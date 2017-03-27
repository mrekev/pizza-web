package lt.rekevicius.pizzaweb.core.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Mindaugas on 2017-03-27.
 */
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Ingredient {
    CHAMPIGNONS("Champignons"),
    OLIVES("Olives");

    private static Map<String, Ingredient> VALUES = Stream.of(Ingredient.values())
            .collect(Collectors.toMap(i -> i.value.toUpperCase(), Function.identity()));

    private String value;

    Ingredient(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Ingredient fromString(String string) {
        Ingredient ingredient = VALUES.get(string.toUpperCase());
        if (ingredient == null) {
            throw new IllegalArgumentException(string + " has no corresponding ingredient value");
        }
        return ingredient;
    }

}
