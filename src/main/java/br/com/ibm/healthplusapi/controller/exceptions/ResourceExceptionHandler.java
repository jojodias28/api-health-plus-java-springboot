package br.com.ibm.healthplusapi.controller.exceptions;

import br.com.ibm.healthplusapi.service.exceptions.ExistingEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ExistingEmailException.class)
    public ResponseEntity<StandardError> resourceAlreadyExists(ExistingEmailException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError(status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);

    }

}
