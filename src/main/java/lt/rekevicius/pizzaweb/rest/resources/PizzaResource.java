package lt.rekevicius.pizzaweb.rest.resources;

import lt.rekevicius.pizzaweb.core.entities.Ingredient;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Mindaugas on 2017-03-22.
 */
public class PizzaResource extends ResourceSupport {

    @NotBlank(message = "error.pizza.title.not.blank")
    private String title;

    @NotBlank(message = "error.pizza.description.not.blank")
    private String description;

    @Size(min = 3, message = "error.pizza.ingredients.min.length")
    @NotEmpty(message = "error.pizza.ingredients.min.length")
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
