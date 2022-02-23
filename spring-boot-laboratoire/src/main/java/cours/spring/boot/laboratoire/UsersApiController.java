package cours.spring.boot.laboratoire;

import cours.spring.boot.laboratoire.dao.UsersDAO;
import cours.spring.boot.laboratoire.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/users")   // GET /users -> 200 OK
    public List<User> getUsers() {
        return dao.list();
    }

    @PostMapping("/users")
    public ResponseEntity<User> create(@RequestBody User body) {  // new User() -> JSON -> binding process (BindingResult) ->
        User created = dao.create(body);

        ResponseEntity<User> response = new ResponseEntity<User>(created, HttpStatus.CREATED);  // 201
        response.getHeaders().add("Location", "http://localhost:8080/users/" + created.getId());

        return response;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") long id) {
        User found = dao.get(id);

        if(found == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(found, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody User body) {
        User updated = dao.update(id, body);

        if(updated == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);  // 203 (idempotente -> read only)
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        Long deleted = dao.delete(id);

        if(deleted == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
