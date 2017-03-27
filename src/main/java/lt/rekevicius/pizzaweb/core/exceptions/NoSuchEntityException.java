package lt.rekevicius.pizzaweb.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Mindaugas on 2017-03-27.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchEntityException extends RuntimeException {
    public NoSuchEntityException(String msg) {
        super(msg);
    }
}
