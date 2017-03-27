package lt.rekevicius.pizzaweb.core.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.MessageFormat;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Mindaugas on 2017-03-27.
 */
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Ingredient {
    BACON("Bacon"),
    BERN_SAUCE("Bern sauce"),
    CHAMPIGNONS("Champignons"),
    CHICKEN("Chicken"),
    CHEESE("Cheese"),
    HOT_PEPPERS("Hot peppers"),
    HOT_SAUCE("Hot sauce"),
    JALAPA_PEPPERS("Jalapa peppers"),
    MINCED_PORK("Minced pork"),
    MOULDED_BEEF_HAM("Moulded beef ham"),
    MOULDED_PORK_HAM("Moulded pork ham"),
    OLIVES("Olives"),
    ONIONS("Onions"),
    PORK_HAM("Pork ham"),
    PICKLED_BELL_PEPPERS("Pickled bell peppers"),
    PICKLED_ONIONS("Pickled onions"),
    SALAMI("Salami"),
    TOMATOES("Tomatoes"),
    TUNA("Tuna");

    private static Map<String, Ingredient> VALUES = Stream.of(Ingredient.values())
            .collect(Collectors.toMap(ingredient -> ingredient.value.toUpperCase(), Function.identity()));

    private String value;

    Ingredient(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Ingredient of(String value) {
        Ingredient ingredient;
        if (value == null || null == (ingredient = VALUES.get(value.toUpperCase()))) {
            throw new IllegalArgumentException(MessageFormat.format("{0} has no corresponding ingredient value", value));
        }
        return ingredient;
    }
}
