package lt.rekevicius.pizzaweb.rest.resources;

import lt.rekevicius.pizzaweb.core.entities.Ingredient;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by Mindaugas on 2017-03-22.
 */
public class PizzaResource extends ResourceSupport {

    private String title;

    private List<Ingredient> ingredients;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }
}
