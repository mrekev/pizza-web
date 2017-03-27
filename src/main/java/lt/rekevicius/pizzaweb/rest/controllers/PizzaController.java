package lt.rekevicius.pizzaweb.rest.controllers;

import lt.rekevicius.pizzaweb.core.entities.impl.Pizza;
import lt.rekevicius.pizzaweb.core.repositories.PizzaEntityRepository;
import lt.rekevicius.pizzaweb.core.repositories.specifications.impl.PizzaQueryByIdSpecification;
import lt.rekevicius.pizzaweb.rest.exceptions.ResourceNotFoundException;
import lt.rekevicius.pizzaweb.rest.resources.PizzaResource;
import lt.rekevicius.pizzaweb.rest.resources.asm.PizzaResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @GetMapping("/")
    public List<PizzaResource> getPizzas() {
        List<Pizza> pizzas = pizzaEntityRepository.query(pizza -> true);
        List<PizzaResource> pizzaResources = pizzaResourceAsm.toResources(pizzas);
        return pizzaResources;
    }

    @GetMapping("/{pizzaId}")
    public PizzaResource getPizza(@PathVariable Long pizzaId) {
        PizzaQueryByIdSpecification qs = new PizzaQueryByIdSpecification(pizzaId);
        List<Pizza> pizzas = pizzaEntityRepository.query(qs);
        if (pizzas.isEmpty()) {
            throw new ResourceNotFoundException("Ooops... pizza you were looking for was not found!");
        }
        PizzaResource resource = pizzaResourceAsm.toResource(pizzas.get(0));
        return resource;
    }

    @PostMapping("/")
    public void addPizza(@RequestBody PizzaResource pizzaResource, HttpServletResponse response) throws IOException {
        Pizza pizza = pizzaResourceAsm.toEntity(pizzaResource);
        pizzaEntityRepository.add(pizza);
        response.setHeader(HttpHeaders.LOCATION, getPizzaLocation(pizza));
        response.setStatus(HttpStatus.CREATED.value());
    }

    @PutMapping("/{pizzaId}")
    public void updatePizza(@PathVariable Long pizzaId, @RequestBody PizzaResource pizzaResource, HttpServletResponse response) throws IOException {
        Pizza pizza = pizzaResourceAsm.toEntity(pizzaResource);
        pizza.setId(pizzaId);
        pizzaEntityRepository.update(pizza);
        response.setHeader(HttpHeaders.LOCATION, getPizzaLocation(pizza));
        response.setStatus(HttpStatus.SEE_OTHER.value());
    }

    @DeleteMapping("/{pizzaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePizza(@PathVariable Long pizzaId, @RequestBody PizzaResource pizzaResource) throws IOException {
        Pizza pizza = pizzaResourceAsm.toEntity(pizzaResource);
        pizza.setId(pizzaId);
        pizzaEntityRepository.remove(pizza);
    }

    private String getPizzaLocation(Pizza pizza) {
        return "/rest/pizzas/" + pizza.getId();
    }
}
