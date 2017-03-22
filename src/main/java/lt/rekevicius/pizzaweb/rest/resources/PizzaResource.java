package lt.rekevicius.pizzaweb.rest.resources;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Mindaugas on 2017-03-22.
 */
public class PizzaResource extends ResourceSupport {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
