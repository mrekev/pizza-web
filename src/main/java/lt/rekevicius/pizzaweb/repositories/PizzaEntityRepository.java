package lt.rekevicius.pizzaweb.repositories;

import lt.rekevicius.pizzaweb.core.entities.impl.Pizza;
import lt.rekevicius.pizzaweb.repositories.specifications.PizzaQuerySpecification;

/**
 * Created by Mindaugas on 2017-03-22.
 */
public interface PizzaEntityRepository extends EntityRepository<Pizza, PizzaQuerySpecification> {
}
