package ensf614.project.team6.cinema.api.mappers;

import ensf614.project.team6.cinema.domain.bank.exceptions.RefundNotAvailable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RefundNotAvailableExceptionMapper {

    @ResponseBody
    @ExceptionHandler(RefundNotAvailable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handler() {
        return "You can't refund this ticket";
    }
}