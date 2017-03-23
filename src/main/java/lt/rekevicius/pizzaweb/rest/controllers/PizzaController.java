package lt.rekevicius.pizzaweb.rest.controllers;

import lt.rekevicius.pizzaweb.core.entities.impl.Pizza;
import lt.rekevicius.pizzaweb.core.repositories.PizzaEntityRepository;
import lt.rekevicius.pizzaweb.core.repositories.specifications.impl.PizzaQueryByIdSpecification;
import lt.rekevicius.pizzaweb.rest.exceptions.ResourceNotFoundException;
import lt.rekevicius.pizzaweb.rest.resources.PizzaResource;
import lt.rekevicius.pizzaweb.rest.resources.asm.PizzaResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        Pizza pizza = new Pizza();
        pizza.setTitle(pizzaResource.getTitle());
        pizzaEntityRepository.add(pizza);
        //TODO add check for success and throw exception if failed
        String location = "/rest/pizzas/" + pizza.getId();
//        response.setHeader(HttpHeaders.LOCATION, location);
//        response.setStatus(HttpStatus.CREATED.value());
        response.sendRedirect(location);
    }

    @PutMapping("/{pizzaId}")
    public void updatePizza(@PathVariable Long pizzaId, @RequestBody PizzaResource pizzaResource, HttpServletResponse response) throws IOException {
        Pizza pizza = new Pizza();
        pizza.setTitle(pizzaResource.getTitle());
        pizza.setId(pizzaId);
        pizzaEntityRepository.update(pizza);
        //TODO add check for success and throw exception if failed
        String location = "/rest/pizzas/" + pizza.getId();
//        response.setHeader(HttpHeaders.LOCATION, location);
//        response.setStatus(HttpStatus.CREATED.value());
        //TODO fix this to stop redirecting to location with PUT method
        response.sendRedirect(location);
    }

    @DeleteMapping("/{pizzaId}")
    public void deletePizza(@PathVariable Long pizzaId, @RequestBody PizzaResource pizzaResource, HttpServletResponse response) throws IOException {
        Pizza pizza = new Pizza();
        pizza.setTitle(pizzaResource.getTitle());
        pizza.setId(pizzaId);
        pizzaEntityRepository.remove(pizza);
        //TODO add check for success and throw exception if failed
        String location = "/rest/pizzas/";
//        response.setHeader(HttpHeaders.LOCATION, location);
//        response.setStatus(HttpStatus.CREATED.value());
        //TODO fix this to stop redirecting to location with DELETE method
        response.sendRedirect(location);
    }


}
