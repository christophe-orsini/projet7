package com.ocdev.biblio.apibiblio.services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
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
import com.ocdev.biblio.apibiblio.entities.Utilisateur;
import com.ocdev.biblio.apibiblio.errors.AlreadyExistsException;
import com.ocdev.biblio.apibiblio.errors.DelayLoanException;
import com.ocdev.biblio.apibiblio.errors.EntityNotFoundException;
import com.ocdev.biblio.apibiblio.errors.NotEnoughCopiesException;
import com.ocdev.biblio.apibiblio.utils.AppSettings;

@Service
public class PretServiceImpl implements PretService
{
	@Autowired PretRepository pretRepository;
	@Autowired OuvrageRepository ouvrageRepository;
	@Autowired UtilisateurRepository utilisateurRepository;
	@Autowired IDtoConverter<Pret, PretDto> pretConverter;
	
	@Override
	@Transactional
	public Pret creer(Long abonneId, Long ouvrageId) throws AlreadyExistsException, EntityNotFoundException, NotEnoughCopiesException
	{
		// verfifier si l'abonné existe
		Optional<Utilisateur> abonne = utilisateurRepository.findById(abonneId);
		if (!abonne.isPresent()) throw new EntityNotFoundException("L'abonné n'existe pas");
				
		// verfifier si l'ouvrage existe
		Optional<Ouvrage> ouvrage = ouvrageRepository.findById(ouvrageId);
		if (!ouvrage.isPresent()) throw new EntityNotFoundException("L'ouvrage n'existe pas");
		
		// recherche si un pret en cours existe deja
		Optional<Pret> pretExists = pretRepository.findByAbonneIdAndOuvrageIdAndStatutNot(abonneId, ouvrageId, Statut.RETOURNE);
		if (pretExists.isPresent()) throw new AlreadyExistsException("Un prêt en cours existe déjà pour cet abonné et cet ouvrage");		
			
		// verifier s'il y a assez d'exemplaires d'ouvrage
		if (ouvrage.get().getNbreExemplaire() < 1) throw new NotEnoughCopiesException("Pas assez d'exemplaires pour le prêt de cet ouvrage");
		
		// mettre a jour le nombre d'exemplaires
		ouvrage.get().setNbreExemplaire(ouvrage.get().getNbreExemplaire() - 1);
		ouvrageRepository.save(ouvrage.get());
		
		// initialisation du pret
		Pret pret = new Pret(abonne.get(), ouvrage.get());
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_MONTH, AppSettings.getIntSetting("duree-pret"));
		pret.setDateFinPrevu(c.getTime());
		
		pret.setStatut(Statut.EN_COURS);
		
		// creation du pret
		return pretRepository.save(pret);
	}

	@Override
	public void retournerOuvrage(Long pretId) throws EntityNotFoundException
	{
		Optional<Pret> pret = pretRepository.findById(pretId);
		if (!pret.isPresent()) throw new EntityNotFoundException("Le prêt n'existe pas");
		
		// mettre a jour le nombre d'exemplaires
		Ouvrage ouvrage = pret.get().getOuvrage();
		ouvrage.setNbreExemplaire(ouvrage.getNbreExemplaire() + 1);
		pret.get().setOuvrage(ouvrage);
				
		// set date de retour
		pret.get().setDateRetour(new Date());
		// changer statut
		pret.get().setStatut(Statut.RETOURNE);
		// sauvegarder
		pretRepository.save(pret.get());
	}

	@Override
	public Pret prolonger(Long pretId) throws EntityNotFoundException, DelayLoanException
	{
		Optional<Pret> pret = pretRepository.findById(pretId);
		if (!pret.isPresent()) throw new EntityNotFoundException("Le prêt avec l'ID " + pretId + " n'existe pas");
		
		// verifier si le pret a deja été prolongé
		int nbProlongations = AppSettings.getIntSetting("nbre-prolongations");
		if (nbProlongations <= pret.get().getNbreProlongations()) throw new DelayLoanException("Le prêt avec ne peut plus être prolongé");
		
		// prolongation
		Calendar c = Calendar.getInstance();
		c.setTime(pret.get().getDateDebut());
		c.add(Calendar.DAY_OF_MONTH, AppSettings.getIntSetting("duree-pret") * nbProlongations);
		Date nouvelleDateFin = c.getTime();
		
		// set nouvelle date de fin prevue
		pret.get().setDateFinPrevu(nouvelleDateFin);
		
		// set nouveau statut
		pret.get().setStatut(Statut.PROLONGE);
		
		// sauvegarder
		return pretRepository.save(pret.get());
	}

	@Override
	public Collection<Pret> listerSesPrets(Long abonneId) throws EntityNotFoundException
	{
		// verifier si l'abonné existe
		Optional<Utilisateur> abonne = utilisateurRepository.findById(abonneId);
		if (!abonne.isPresent()) throw new EntityNotFoundException("L'abonné n'existe pas");
		
		return pretRepository.findByAbonneIdAndStatutNotAndStatutNot(abonneId, Statut.INCONNU, Statut.RETOURNE);
	}

	@Override
	public Pret consulter(Long pretId) throws EntityNotFoundException
	{
		Optional<Pret> pret = pretRepository.findById(pretId);
		if (!pret.isPresent()) throw new EntityNotFoundException("Le prêt n'existe pas");
		
		return pret.get();
	}
}
