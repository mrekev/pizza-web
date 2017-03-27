package lt.rekevicius.pizzaweb.rest.resources.asm;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mindaugas on 2017-03-27.
 */
public abstract class ResourceEntityAssemblerSupport<T, D extends ResourceSupport> extends ResourceAssemblerSupport<T, D> {

    /**
     * Creates a new {@link ResourceAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public ResourceEntityAssemblerSupport(Class<?> controllerClass, Class<D> resourceType) {
        super(controllerClass, resourceType);
    }

    public abstract T toEntity(D resource);

    public List<T> toEntities(Iterable<? extends D> resources) {
        List<T> result = new ArrayList<>();

        if (resources != null) {
            for (D resource : resources) {
                result.add(toEntity(resource));
            }
        }
        return result;
    }
}
