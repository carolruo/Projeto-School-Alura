package br.com.alura.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {

        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), "O valor dos campos é inválido", System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(DuplicateEnrollmentException.class)
    public ResponseEntity<StandardError> duplicateEnrollment(DuplicateEnrollmentException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<StandardError> noEnrollmentsFound(NoContentException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.NO_CONTENT.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(err);
    }

    @ExceptionHandler(DuplicateObjectException.class)
    public ResponseEntity<StandardError> duplicateObjectException(DuplicateObjectException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(UnauthorizedRoleAccessException.class)
    public ResponseEntity<StandardError> unauthorizedRoleAccessException(UnauthorizedRoleAccessException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }
}
