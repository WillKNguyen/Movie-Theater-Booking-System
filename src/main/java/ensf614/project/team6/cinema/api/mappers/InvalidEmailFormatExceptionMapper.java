package ensf614.project.team6.cinema.api.mappers;

import ensf614.project.team6.cinema.domain.user.exceptions.InvalidEmailFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidEmailFormatExceptionMapper {

    @ResponseBody
    @ExceptionHandler(InvalidEmailFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handler() {
        return "Invalid email format";
    }
}