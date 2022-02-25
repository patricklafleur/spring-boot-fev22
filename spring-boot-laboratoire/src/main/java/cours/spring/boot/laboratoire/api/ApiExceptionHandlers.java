package cours.spring.boot.laboratoire.api;

import cours.spring.boot.laboratoire.persistence.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice  // Advice = Décorateur
public class ApiExceptionHandlers {
    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandlers.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrors> handle(MethodArgumentNotValidException e) {
        return new ResponseEntity<ApiErrors>(new ApiErrors(e.getBindingResult()),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Void> handleObjectNotFound(ObjectNotFoundException e) {
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
}
