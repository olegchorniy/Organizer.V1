package kpi.ipt.organizer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalUserParametersException extends RuntimeException {

    public IllegalUserParametersException(Throwable cause) {
        super(cause);
    }
}
