package com.ocdev.biblio.apibiblio.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ocdev.biblio.apibiblio.entities.Pret;

/**
 * Interface d'accès au données de la classe {@link com.ocdev.biblio.apibiblio.entities.Pret}.
 * Utilise JPA.
 * @author C.Orsini
 *
 */
@Repository
public interface PretRepository extends JpaRepository<Pret, Long>
{	
	@Query("SELECT t FROM #{#entityName} t WHERE :abonneId = abonne_id AND :ouvrageId = ouvrage_id AND statut != 'RETOURNE'")
	public Collection<Pret> findPretsActifs(@Param("abonneId") Long abonneId, @Param("ouvrageId") Long ouvrageId);
	
	@Query("SELECT t FROM #{#entityName} t WHERE :abonneId = abonne_id AND statut = 'EN_COURS' OR statut = 'PROLONGE' OR statut = 'RETARDE'")
	public Collection<Pret> findPretsParAbonneActifs(@Param("abonneId") Long abonneId);
}
