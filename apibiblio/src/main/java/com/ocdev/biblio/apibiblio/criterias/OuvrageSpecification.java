package com.ocdev.biblio.apibiblio.criterias;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import com.ocdev.biblio.apibiblio.entities.Ouvrage;
import com.ocdev.biblio.apibiblio.entities.Theme;

public class OuvrageSpecification implements Specification<Ouvrage>
{
	private SearchCriteria criteria;
	
	public OuvrageSpecification(SearchCriteria criteria)
	{
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<Ouvrage> root, CriteriaQuery<?> query, CriteriaBuilder cb)
	{
		if (criteria.getOperation().equalsIgnoreCase(">"))
		{
			if (root.get(criteria.getKey()).getJavaType().equals(int.class))
			{
				return cb.greaterThan(root.<Integer> get(criteria.getKey()), Integer.parseInt(criteria.getValue().toString()));
			}
			else return null;
        }
		else if (criteria.getOperation().equalsIgnoreCase(">="))
        {
			if (root.get(criteria.getKey()).getJavaType().equals(int.class))
			{
				return cb.greaterThanOrEqualTo(root.<Integer> get(criteria.getKey()), Integer.parseInt(criteria.getValue().toString()));
			}
			else return null;
        }
        else if (criteria.getOperation().equalsIgnoreCase("<"))
        {
        	if (root.get(criteria.getKey()).getJavaType().equals(int.class))
			{
				return cb.lessThan(root.<Integer> get(criteria.getKey()), Integer.parseInt(criteria.getValue().toString()));
			}
			else return null;
        }
        else if (criteria.getOperation().equalsIgnoreCase("<="))
        {
        	if (root.get(criteria.getKey()).getJavaType().equals(int.class))
			{
				return cb.lessThanOrEqualTo(root.<Integer> get(criteria.getKey()), Integer.parseInt(criteria.getValue().toString()));
			}
			else return null;
        }
        else if (criteria.getOperation().equalsIgnoreCase("=="))
        {
        	if (root.get(criteria.getKey()).getJavaType().equals(String.class))
            {
                return cb.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } 
            else if (root.get(criteria.getKey()).getJavaType().equals(Theme.class))
            {
                return null; // TODO
            }
            else if (root.get(criteria.getKey()).getJavaType().equals(int.class))
            {
            	return cb.equal(root.<Integer> get(criteria.getKey()), Integer.parseInt(criteria.getValue().toString()));
            }
            else return null;
        }
        else if (criteria.getOperation().equalsIgnoreCase("!="))
        {
        	if (root.get(criteria.getKey()).getJavaType().equals(String.class))
            {
                return cb.notLike(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } 
            else if (root.get(criteria.getKey()).getJavaType().equals(Theme.class))
            {
                return null; // TODO
            }
            else if (root.get(criteria.getKey()).getJavaType().equals(int.class))
            {
            	return cb.notEqual(root.<Integer> get(criteria.getKey()), Integer.parseInt(criteria.getValue().toString()));
            }
            else return null;
        }
        return null;
	}
}
