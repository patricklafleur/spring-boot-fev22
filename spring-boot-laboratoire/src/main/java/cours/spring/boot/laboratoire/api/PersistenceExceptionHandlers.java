package cours.spring.boot.laboratoire.api;

import cours.spring.boot.laboratoire.persistence.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice  // Advice = Décorateur
public class PersistenceExceptionHandlers {
    private static final Logger logger = LoggerFactory.getLogger(PersistenceExceptionHandlers.class);

    @ExceptionHandler(ObjectNotFoundException.class)
    @Order(10)
    public ResponseEntity<Void> handleObjectNotFound(ObjectNotFoundException e) {
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
}
