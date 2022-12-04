package ensf614.project.team6.cinema.api.mappers;

import ensf614.project.team6.cinema.domain.bank.exceptions.PaymentAlreadyRefunded;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PaymentAlreadyRefundedExceptionMapper {

    @ResponseBody
    @ExceptionHandler(PaymentAlreadyRefunded.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String handler() {
        return "A payment cannot be refunded twice";
    }
}