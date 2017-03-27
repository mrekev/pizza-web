package lt.rekevicius.pizzaweb.core.entities.impl;

import lt.rekevicius.pizzaweb.core.entities.Entity;
import lt.rekevicius.pizzaweb.core.entities.Ingredient;

import java.util.List;

/**
 * Created by Mindaugas on 2017-03-20.
 */
public class Pizza extends Entity {

    private String title;

    private String description;

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
