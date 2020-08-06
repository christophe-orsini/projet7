package com.ocdev.biblio.apibiblio.services;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ocdev.biblio.apibiblio.assemblers.IDtoConverter;
import com.ocdev.biblio.apibiblio.dao.OuvrageRepository;
import com.ocdev.biblio.apibiblio.dao.PretRepository;
import com.ocdev.biblio.apibiblio.dao.UtilisateurRepository;
import com.ocdev.biblio.apibiblio.dto.PretDto;
import com.ocdev.biblio.apibiblio.entities.Ouvrage;
import com.ocdev.biblio.apibiblio.entities.Pret;
import com.ocdev.biblio.apibiblio.entities.Role;
import com.ocdev.biblio.apibiblio.entities.Statut;
import com.ocdev.biblio.apibiblio.entities.Utilisateur;
import com.ocdev.biblio.apibiblio.errors.AlreadyExistsException;
import com.ocdev.biblio.apibiblio.errors.DelayLoanException;
import com.ocdev.biblio.apibiblio.errors.EntityNotFoundException;
import com.ocdev.biblio.apibiblio.errors.NotAllowedException;
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
		
		// mise à jour du nombre d'exemplaires
		ouvrage.get().setNbreExemplaire(ouvrage.get().getNbreExemplaire() - 1);
		
		// initialisation du pret
		Pret pret = new Pret(abonne.get(), ouvrage.get());
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		pret.setDateDebut(c.getTime());
		c.add(Calendar.DAY_OF_MONTH, AppSettings.getIntSetting("duree-pret"));
		pret.setDateFinPrevu(c.getTime());
		
		pret.setStatut(Statut.EN_COURS);
		
		// Initialiser le nombre de prolongations possible
		pret.setProlongationsPossible(AppSettings.getIntSetting("nbre-prolongations"));
		// sauvegarde de l'ouvrage
		ouvrageRepository.save(ouvrage.get());
		
		// creation du pret
		return pretRepository.save(pret);
	}

	@Override
	@Transactional
	public void retournerOuvrage(Long pretId, Long utilisateurId) throws EntityNotFoundException, NotAllowedException
	{
		Optional<Pret> pret = pretRepository.findById(pretId);
		if (!pret.isPresent()) throw new EntityNotFoundException("Le prêt n'existe pas");
		
		// verifier si le pret n'est pas déja retourné
		if (pret.get().getStatut() == Statut.RETOURNE) return;
		
		// verifier si le demandeur existe
		Optional<Utilisateur> demandeur = utilisateurRepository.findById(utilisateurId);
		if (!demandeur.isPresent()) throw new EntityNotFoundException("Le demandeur n'existe pas");
		
		// verifier si le demandeur est l'emprunteur ou un employé
		Utilisateur emprunteur = pret.get().getAbonne();
		if (demandeur.get().getRole() == Role.ROLE_ABONNE && demandeur.get().getId() != emprunteur.getId())
			throw new NotAllowedException("Vous ne pouvez pas retourner ce prêt. Vous n'etes pas l'emprunteur");
				
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
	public Pret prolonger(Long pretId, Long utilisateurId) throws EntityNotFoundException, DelayLoanException, NotAllowedException
	{
		Optional<Pret> pret = pretRepository.findById(pretId);
		if (!pret.isPresent()) throw new EntityNotFoundException("Le prêt n'existe pas");
		
		// verifier si le pret peut etre prolongé
		if (pret.get().getProlongationsPossible() <= 0) throw new DelayLoanException("Le prêt ne peut plus être prolongé");
		
		// verifier si le demandeur est l'emprunteur ou un employé
		Utilisateur utilisateur = pret.get().getAbonne();
		if (utilisateur.getRole() == Role.ROLE_ABONNE && utilisateur.getId() != utilisateurId)
			throw new NotAllowedException("Vous ne pouvez pas prolonger ce prêt. Vous n'etes pas l'emprunteur");
		
		// prolongation
		Calendar c = Calendar.getInstance();
		c.setTime(pret.get().getDateDebut());
		c.add(Calendar.DAY_OF_MONTH, AppSettings.getIntSetting("duree-pret"));
		Date nouvelleDateFin = c.getTime();
		
		// set nouvelle date de fin prevue
		pret.get().setDateFinPrevu(nouvelleDateFin);
		
		// set nouveau statut
		pret.get().setStatut(Statut.PROLONGE);
		pret.get().setProlongationsPossible(pret.get().getProlongationsPossible() - 1);
		
		// sauvegarder
		return pretRepository.save(pret.get());
	}

	@Override
	public Page<Pret> listerSesPrets(Long abonneId, Pageable paging) throws EntityNotFoundException
	{
		// verifier si l'abonné existe
		Optional<Utilisateur> abonne = utilisateurRepository.findById(abonneId);
		if (!abonne.isPresent()) throw new EntityNotFoundException("L'abonné n'existe pas");
		
		return pretRepository.findByAbonneIdAndStatutNotAndStatutNot(abonneId, Statut.INCONNU, Statut.RETOURNE, paging);
	}

	@Override
	public Pret consulter(Long pretId) throws EntityNotFoundException
	{
		Optional<Pret> pret = pretRepository.findById(pretId);
		if (!pret.isPresent()) throw new EntityNotFoundException("Le prêt n'existe pas");
		
		return pret.get();
	}
}
