package lt.rekevicius.pizzaweb.core.repositories.impl;

import lt.rekevicius.pizzaweb.core.entities.impl.Pizza;
import lt.rekevicius.pizzaweb.core.exceptions.EntityAlreadyExistsException;
import lt.rekevicius.pizzaweb.core.exceptions.NoSuchEntityException;
import lt.rekevicius.pizzaweb.core.repositories.PizzaEntityRepository;
import lt.rekevicius.pizzaweb.core.repositories.specifications.PizzaQuerySpecification;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.Map.Entry;

/**
 * Created by Mindaugas on 2017-03-22.
 */
@Repository("inMemory")
public class PizzaEntityRepositoryInMemoryImpl implements PizzaEntityRepository {

    private static final Map<Long, Pizza> pizzaMap = new HashMap<>();

    private static Long id = 0L;

    @Override
    public void add(Pizza entity) {
        List<Pizza> pizzas = query(pizza -> pizza.getTitle().equals(entity.getTitle()));
        if (!pizzas.isEmpty()) {
            throw new EntityAlreadyExistsException("Pizza with such title already exists!");
        }
        Long id = generateId();
        entity.setId(id);
        pizzaMap.put(id, entity);
        id++;
    }

    @Override
    public void remove(Pizza entity) {
        List<Pizza> pizzas = query(pizza -> pizza.getId().equals(entity.getId()));
        if (pizzas.isEmpty()) {
            throw new NoSuchEntityException("The pizza you are trying to delete does not exist!");
        }
        pizzaMap.remove(entity.getId());
    }

    @Override
    public void update(Pizza entity) {
        pizzaMap.put(entity.getId(), entity);
    }

    @Override
    public void modify(PizzaQuerySpecification qs) {

    }

    @Override
    public List<Pizza> query(PizzaQuerySpecification qs) {
        List<Pizza> pizzas = new ArrayList<>();
        Set<Entry<Long, Pizza>> entrySet = pizzaMap.entrySet();
        for (Entry<Long, Pizza> entry : entrySet) {
            if (qs.specified(entry.getValue())) {
                pizzas.add(entry.getValue());
            }
        }
        return pizzas;
    }

    private Long generateId() {
        Long id = PizzaEntityRepositoryInMemoryImpl.id;
        while (pizzaMap.containsKey(id)) {
            id++;
        }
        PizzaEntityRepositoryInMemoryImpl.id = id;
        return id;
    }
}
