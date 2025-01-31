package br.com.jeronimo.springMongo.resources.exception;

import br.com.jeronimo.springMongo.services.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
    StandardError error = new StandardError(
        System.currentTimeMillis(),
        HttpStatus.NOT_FOUND.value(),
        "Não encontrado",
        e.getMessage(),
        request.getRequestURI()
    );
    return ResponseEntity.status(error.getStatus()).body(error);
  }

}
