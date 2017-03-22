package lt.rekevicius.pizzaweb.repositories.specifications.impl;

import lt.rekevicius.pizzaweb.core.entities.impl.Pizza;
import lt.rekevicius.pizzaweb.repositories.specifications.PizzaQuerySpecification;

/**
 * Created by Mindaugas on 2017-03-22.
 */
public class PizzaQueryByIdSpecification implements PizzaQuerySpecification {

    private Long id;

    public PizzaQueryByIdSpecification(Long id) {
        this.id = id;
    }

    @Override
    public boolean specified(Pizza entity) {
        return id.equals(entity.getId());
    }
}
