package lt.rekevicius.pizzaweb.core.entities;

/**
 * Created by Mindaugas on 2017-03-22.
 */
public abstract class Entity {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
