package lt.rekevicius.pizzaweb.core.repositories.impl;

import lt.rekevicius.pizzaweb.core.entities.impl.Pizza;
import lt.rekevicius.pizzaweb.core.repositories.PizzaEntityRepository;
import lt.rekevicius.pizzaweb.core.repositories.specifications.PizzaQuerySpecification;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * Created by Mindaugas on 2017-03-22.
 */
@Repository
public class PizzaEntityRepositoryImpl implements PizzaEntityRepository {
    @Override
    public void add(Pizza entity) {

    }

    @Override
    public void remove(Pizza entity) {

    }

    @Override
    public void update(Pizza entity) {

    }

    @Override
    public void modify(PizzaQuerySpecification qs) {

    }

    @Override
    public List<Pizza> query(PizzaQuerySpecification qs) {
        return Collections.emptyList();
    }
}
