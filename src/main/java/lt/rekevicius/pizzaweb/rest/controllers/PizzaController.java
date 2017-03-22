package lt.rekevicius.pizzaweb.rest.controllers;

import lt.rekevicius.pizzaweb.core.entities.impl.Pizza;
import lt.rekevicius.pizzaweb.repositories.PizzaEntityRepository;
import lt.rekevicius.pizzaweb.repositories.specifications.PizzaQuerySpecification;
import lt.rekevicius.pizzaweb.repositories.specifications.QuerySpecification;
import lt.rekevicius.pizzaweb.repositories.specifications.impl.PizzaQueryByIdSpecification;
import lt.rekevicius.pizzaweb.rest.exceptions.ResourceNotFoundException;
import lt.rekevicius.pizzaweb.rest.resources.PizzaResource;
import lt.rekevicius.pizzaweb.rest.resources.asm.PizzaResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Mindaugas on 2017-03-20.
 */
@RestController
@RequestMapping(value = "/rest/pizzas")
public class PizzaController {

    @Autowired
    @Qualifier("inMemory")
    private PizzaEntityRepository pizzaEntityRepository;

    @Autowired
    private PizzaResourceAsm pizzaResourceAsm;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<PizzaResource> getPizzas() {
        List<Pizza> pizzas = pizzaEntityRepository.query(pizza -> true);
        if (pizzas.isEmpty()) {
            throw new ResourceNotFoundException("Ooops... pizza you were looking for was not found!");
        }
        List<PizzaResource> pizzaResources = pizzaResourceAsm.toResources(pizzas);
        return pizzaResources;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void addPizza(@RequestBody PizzaResource pizzaResource) {
        Pizza pizza = new Pizza();
        pizza.setTitle(pizzaResource.getTitle());
        pizzaEntityRepository.add(pizza);
    }

    @RequestMapping(value = "/{pizzaId}", method = RequestMethod.GET)
    public PizzaResource getPizza(@PathVariable Long pizzaId) {
        PizzaQueryByIdSpecification qs = new PizzaQueryByIdSpecification(pizzaId);
        List<Pizza> pizzas = pizzaEntityRepository.query(qs);
        if (pizzas.isEmpty()) {
            throw new ResourceNotFoundException("Ooops... pizza you were looking for was not found!");
        }
        PizzaResource resource = pizzaResourceAsm.toResource(pizzas.get(0));
        return resource;
    }
}
