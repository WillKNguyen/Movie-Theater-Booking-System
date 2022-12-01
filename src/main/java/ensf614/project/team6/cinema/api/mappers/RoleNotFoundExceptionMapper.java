package ensf614.project.team6.cinema.api.mappers;

import ensf614.project.team6.cinema.application.service.exceptions.TicketNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.transaction.RollbackException;

@ControllerAdvice
public class RoleNotFoundExceptionMapper {

  @ResponseBody
  @ExceptionHandler(RollbackException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String handler() {
    return "Role not found";
  }
}