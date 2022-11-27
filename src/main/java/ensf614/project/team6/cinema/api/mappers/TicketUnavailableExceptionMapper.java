package ensf614.project.team6.cinema.api.mappers;

import ensf614.project.team6.cinema.application.service.exceptions.TicketUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TicketUnavailableExceptionMapper {

  @ResponseBody
  @ExceptionHandler(TicketUnavailableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String handler() {
    return "Ticket not available for purchase";
  }
}