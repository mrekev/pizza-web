package lt.rekevicius.pizzaweb.core.repositories;

import lt.rekevicius.pizzaweb.core.entities.Entity;
import lt.rekevicius.pizzaweb.core.repositories.specifications.QuerySpecification;

import java.util.List;

/**
 * Created by Mindaugas on 2017-03-22.
 */
public interface EntityRepository<T extends Entity, K extends QuerySpecification> {
    void add(T entity);

    void remove(T entity);

    void update(T entity);

    void modify(K querySpecification);

    List<T> query(K querySpecification);
}
