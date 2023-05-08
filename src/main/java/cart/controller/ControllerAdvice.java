package cart.controller;

import cart.exception.CartException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> handleNotValidInput(final MethodArgumentNotValidException exception) {
        final List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        final String errorMessages = allErrors.stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.joining(System.lineSeparator()));

        return ResponseEntity.badRequest().body(errorMessages);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(final CartException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
    }
}
