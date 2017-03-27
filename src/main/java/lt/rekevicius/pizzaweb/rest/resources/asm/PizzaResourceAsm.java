package lt.rekevicius.pizzaweb.rest.resources.asm;

import lt.rekevicius.pizzaweb.core.entities.impl.Pizza;
import lt.rekevicius.pizzaweb.rest.controllers.PizzaController;
import lt.rekevicius.pizzaweb.rest.resources.PizzaResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by Mindaugas on 2017-03-22.
 */
@Service
public class PizzaResourceAsm extends ResourceEntityAssemblerSupport<Pizza, PizzaResource> {

    public PizzaResourceAsm() {
        super(PizzaController.class, PizzaResource.class);
    }

    @Override
    public PizzaResource toResource(Pizza entity) {
        PizzaResource resource = new PizzaResource();
        resource.setTitle(entity.getTitle());
        Link link = linkTo(methodOn(PizzaController.class).getPizza(entity.getId())).withSelfRel();
        resource.add(link);
        return resource;
    }

    @Override
    public Pizza toEntity(PizzaResource resource) {
        Pizza pizza = new Pizza();
        pizza.setTitle(resource.getTitle());
        return pizza;
    }
}
