package com.ocdev.biblio.apibiblio.services;

import java.awt.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ocdev.biblio.apibiblio.assemblers.IDtoConverter;
import com.ocdev.biblio.apibiblio.dao.OuvrageRepository;
import com.ocdev.biblio.apibiblio.dao.PretRepository;
import com.ocdev.biblio.apibiblio.dao.UtilisateurRepository;
import com.ocdev.biblio.apibiblio.dto.PretDto;
import com.ocdev.biblio.apibiblio.entities.Ouvrage;
import com.ocdev.biblio.apibiblio.entities.Pret;
import com.ocdev.biblio.apibiblio.entities.Statut;
import com.ocdev.biblio.apibiblio.errors.DelayLoanException;
import com.ocdev.biblio.apibiblio.errors.DuplicateLoanException;
import com.ocdev.biblio.apibiblio.errors.EntityNotFoundException;
import com.ocdev.biblio.apibiblio.errors.NotEnoughCopiesException;

@Service
public class PretServiceImpl implements PretService
{
	private final int DUREE_PRET = 28; // Durée initiale d'un pret en jours
	
	@Autowired PretRepository pretRepository;
	@Autowired OuvrageRepository ouvrageRepository;
	@Autowired UtilisateurRepository utilisateurRepository;
	@Autowired IDtoConverter<Pret, PretDto> pretConverter;
	
	@Override
	@Transactional
	public PretDto creer(PretDto pretDto) throws DuplicateLoanException, EntityNotFoundException, NotEnoughCopiesException
	{
		Pret pret = pretConverter.convertDtoToEntity(pretDto);
		
		// recherche si un pret en cours existe deja
		Collection<Pret> pretsEnCours = pretRepository.findPretsActifs(pret.getAbonne().getId(), pret.getOuvrage().getId());
		if (pretsEnCours.size() > 0) 
		{
			throw new DuplicateLoanException("L'ouvrage avec l'ID " + pret.getOuvrage().getId() +
					" est déjà en prêt pour l'abonné avec l'ID " + pret.getAbonne().getId());
		}
		
		// verfifier si l'ouvrage existe
		Optional<Ouvrage> ouvrage = ouvrageRepository.findById(pret.getOuvrage().getId());
		if (!ouvrage.isPresent()) throw new EntityNotFoundException("L'ouvrage avec l'ID " + pret.getOuvrage().getId() + " n'existe pas");
			
		// verifier s'il y a assez d'exemplaires d'ouvrage
		if (ouvrage.get().getNbreExemplaire() < 1) throw new NotEnoughCopiesException("Pas assez d'exemplaires pour le prêt de l'ouvrage avec l'ID " + 
					ouvrage.get().getId());
		// mettre a jour le nombre d'exemplaires
		ouvrage.get().setNbreExemplaire(ouvrage.get().getNbreExemplaire() - 1);
		ouvrageRepository.save(ouvrage.get());
		
		// initialisation du pret
		Calendar c = Calendar.getInstance();
		c.setTime(pret.getDateDebut());
		c.add(Calendar.DAY_OF_MONTH, DUREE_PRET);
		pret.setDateFinPrevu(c.getTime());
		
		pret.setStatut(Statut.EN_COURS);
		
		// creation du pret
		pret = pretRepository.save(pret);
		
		return pretConverter.convertEntityToDto(pret);
	}

	@Override
	public PretDto retournerOuvrage(Long pretId) throws EntityNotFoundException
	{
		Optional<Pret> pret = pretRepository.findById(pretId);
		if (!pret.isPresent()) throw new EntityNotFoundException("Le prêt avec l'ID " + pretId + " n'existe pas");
		
		// mettre a jour le nombre d'exemplaires
		Optional<Ouvrage> ouvrage = ouvrageRepository.findById(pret.get().getOuvrage().getId());
		if (!ouvrage.isPresent()) throw new EntityNotFoundException("L'ouvrage avec l'ID " + pret.get().getOuvrage().getId() + " n'existe pas");
		ouvrage.get().setNbreExemplaire(ouvrage.get().getNbreExemplaire() + 1);
		ouvrageRepository.save(ouvrage.get());
				
		// set date de retour
		pret.get().setDateRetour(new Date());
		// changer statut
		pret.get().setStatut(Statut.RETOURNE);
		// sauvegarder
		Pret newPret = pretRepository.save(pret.get());
		
		return pretConverter.convertEntityToDto(newPret);
	}

	@Override
	public PretDto prolonger(Long pretId) throws EntityNotFoundException, DelayLoanException
	{
		Optional<Pret> pret = pretRepository.findById(pretId);
		if (!pret.isPresent()) throw new EntityNotFoundException("Le prêt avec l'ID " + pretId + " n'existe pas");
		
		// verifier si le pret a deja été prolongé
		if (pret.get().getStatut() != Statut.EN_COURS) throw new DelayLoanException("Le prêt avec l'ID " + pretId + 
				" ne peut pas être prolongé en raison d'un statut " + pret.get().getStatut().toString());
		
		// verifier si l'on peut prolonger (regle de gestion max 1 fois)
		Date today = new Date();
	
		Calendar c = Calendar.getInstance();
		c.setTime(pret.get().getDateDebut());
		c.add(Calendar.DAY_OF_MONTH, DUREE_PRET * 2);
		Date nouvelleDateFin = c.getTime();
		
		if (nouvelleDateFin.before(today)) throw new DelayLoanException("Cette prolongation entraine le dépassement de la date limite de restitution du " +
				nouvelleDateFin.toString());
		
		// set nouvelle date de fin prevue
		pret.get().setDateFinPrevu(nouvelleDateFin);
		
		// set nouveau statut
		pret.get().setStatut(Statut.PROLONGE);
		
		// sauvegarder
		Pret newPret = pretRepository.save(pret.get());
				
		return pretConverter.convertEntityToDto(newPret);
	}

	@Override
	public Collection<PretDto> listerSesPrets(Long abonneId) throws EntityNotFoundException
	{
		if (!utilisateurRepository.findById(abonneId).isPresent()) throw new EntityNotFoundException("L'utilisateur avec l'ID " + abonneId + " n'existe pas");
		
		Collection<PretDto> prets = new ArrayList<PretDto>();
		
		for (Pret pret : pretRepository.findPretsParAbonneActifs(abonneId))
		{
			prets.add(pretConverter.convertEntityToDto(pret));
		}
		
		return prets;
	}

	@Override
	public PretDto consulter(Long pretId) throws EntityNotFoundException
	{
		Optional<Pret> pret = pretRepository.findById(pretId);
		if (!pret.isPresent()) throw new EntityNotFoundException("Le prêt avec l'ID " + pretId + " n'existe pas");
		
		return pretConverter.convertEntityToDto(pret.get());
	}
}
