package com.ocdev.biblio.apibiblio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
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
	
}
