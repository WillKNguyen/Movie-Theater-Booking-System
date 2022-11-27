package ensf614.project.team6.cinema.api.mappers;

import ensf614.project.team6.cinema.application.service.exceptions.UserAlreadyExistsException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class UserAlreadyExistsExceptionMapper {

  @ResponseBody
  @ExceptionHandler(UserAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String handler() {
    return "Email already taken";
  }
}