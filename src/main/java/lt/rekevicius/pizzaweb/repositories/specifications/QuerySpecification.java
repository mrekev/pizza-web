package lt.rekevicius.pizzaweb.repositories.specifications;

import lt.rekevicius.pizzaweb.core.entities.Entity;

/**
 * Created by Mindaugas on 2017-03-22.
 */
public interface QuerySpecification<T extends Entity> {
    boolean specified(T entity);
}
