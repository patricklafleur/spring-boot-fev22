package cours.spring.boot.laboratoire.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiSanitizerExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApiSanitizerExceptionHandler.class);

}
