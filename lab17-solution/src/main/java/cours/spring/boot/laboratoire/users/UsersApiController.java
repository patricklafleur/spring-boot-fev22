package cours.spring.boot.laboratoire.users;

import cours.spring.boot.laboratoire.api.ApiErrors;
import cours.spring.boot.laboratoire.persistence.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// REST -> Representational Entity State Transfer (JSON, CSV, XML, ?) -> HTTP (GET, POST, PUT, DELETE)
// Entity/Resource (identifiable unique)

// /users -> User[] -> 1..* tables SQL
//    GET   -> fetch all -> 200
//    POST  -> create un nouvel utilisateur -> INSERT -> 200/201

// /users/{id}   -> /users/1
//    GET -> details -> 200
//    PUT -> update -> 200/204
//    DELETE -> détruire -> 200/204

// STATUS
// 2XX: Succès
// 3XX: Réponse partielle (301, 302 -> redirections)
// 4XX: Erreur, faute a été causée par le client
// 5XX: Erreur, faute produite le server

// JSON: JavaScript Object Notation (!function, number, null, undefined, date -> 'iso-8601', "", boolean, object {}, tableau [])

/*
XML:
<users>
    <user id="1">
        <id>1</id>
    </user>
</users>
 */

@RestController  // donne la nature de l'objet (API -> JSON)
public class UsersApiController {
    private static final Logger logger = LoggerFactory.getLogger(UsersApiController.class);

    @Autowired private UsersDAO dao;

    @GetMapping(path="/users")   // GET /users -> 200 OK
    public List<User> getUsers() {
        return dao.list();
    }

    // 1. Transformer le json en classe
    // 2. Valider
    // 3. Déclencher le use case (déléguer @Service)
    // 4. Interpréter le résultat du service
    // 5. Mapper le résultat vers une réponse HTTP

    // Validator.validate(user) -> !valid throw new MethodNotValidArgumentException() -> create(user)
    @PostMapping("/users")
    public ResponseEntity<User> create(@Valid @RequestBody User body) {  // new User() -> JSON -> binding process (BindingResult)  -> Validator -> binding.hasErrors() -> 400
        User created = dao.create(body);
        ResponseEntity<User> response = new ResponseEntity<User>(created, HttpStatus.CREATED);  // 201
        //response.getHeaders().add("Location", "http://localhost:8080/users/" + created.getId());
        return response;
    }


    // try { controller.getById() } catch(Exception e) { if(exceptionHandlers.match(e)) { callExceptionHandler(e) }  else { 500 } }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") long id) throws ObjectNotFoundException {
        return new ResponseEntity<User>(dao.get(id), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") long id, @Valid @RequestBody User body) throws ObjectNotFoundException {
        // DRY = Don't Repeat Yourself
        dao.update(id, body);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);  // 203 (idempotente -> read only)
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) throws ObjectNotFoundException {
        dao.delete(id);  // throw
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

//    @ExceptionHandler(ObjectNotFoundException.class)
//    public ResponseEntity<Void> handleObjectNotFound(ObjectNotFoundException e) {
//        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiErrors> handle(MethodArgumentNotValidException e) {
//        return new ResponseEntity<ApiErrors>(new ApiErrors(e.getBindingResult()),
//                HttpStatus.UNPROCESSABLE_ENTITY);
//    }
}
