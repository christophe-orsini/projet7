package com.ocdev.biblio.apibiblio.dao;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ocdev.biblio.apibiblio.criterias.OuvrageCriteria;
import com.ocdev.biblio.apibiblio.entities.Ouvrage;

/**
 * Interface d'accès au données de la classe {@link com.ocdev.biblio.apibiblio.entities.Ouvrage}.
 * Utilise JPA.
 * @author C.Orsini
 *
 */
@Repository
public interface OuvrageRepository extends JpaRepository<Ouvrage, Long>, JpaSpecificationExecutor<Ouvrage>
{
	Optional<Ouvrage> findByTitreIgnoreCase(String titre);
	
	default Collection<Ouvrage> searchOuvrages(OuvrageCriteria criteria)
	{
		return findAll((root, query, cb) ->
		{
			return cb.lessThan(root.get("id"), criteria.getId());
		});
	}
}
