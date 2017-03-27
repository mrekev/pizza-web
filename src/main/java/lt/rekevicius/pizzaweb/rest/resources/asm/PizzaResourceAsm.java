package lt.rekevicius.pizzaweb.rest.resources.asm;

import lt.rekevicius.pizzaweb.core.entities.impl.Pizza;
import lt.rekevicius.pizzaweb.rest.controllers.PizzaController;
import lt.rekevicius.pizzaweb.rest.resources.PizzaResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
        resource.setDescription(entity.getDescription());
        resource.setIngredients(entity.getIngredients());
        Link link = linkTo(methodOn(PizzaController.class).getPizza(entity.getId())).withSelfRel();
        resource.add(link);
        return resource;
    }

    @Override
    public Pizza toEntity(PizzaResource resource) {
        Pizza pizza = new Pizza();
        pizza.setIngredients(resource.getIngredients());
        pizza.setDescription(resource.getDescription());
        pizza.setTitle(resource.getTitle());
        return pizza;
    }
}
