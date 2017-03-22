package lt.rekevicius.pizzaweb.repositories.impl;

import lt.rekevicius.pizzaweb.core.entities.impl.Pizza;
import lt.rekevicius.pizzaweb.repositories.PizzaEntityRepository;
import lt.rekevicius.pizzaweb.repositories.specifications.PizzaQuerySpecification;
import lt.rekevicius.pizzaweb.repositories.specifications.QuerySpecification;
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
