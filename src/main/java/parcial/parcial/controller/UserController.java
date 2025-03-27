package parcial.parcial.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import parcial.parcial.model.User;
import parcial.parcial.service.ServiceUser;

// http://localhost:8080/user/

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ServiceUser serviceUser;

    /**
     * Crea un nuevo usuario en la base de datos.
     * @param user Objeto de tipo User que se debe almacenar.
     * @return ResponseEntity con el usuario creado y el código de estado HTTP 201 (CREATED).
     */
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(serviceUser.createUser(user), HttpStatus.CREATED);
    }

    /**
     * Obtiene la lista de todos los usuarios almacenados.
     * @return ResponseEntity con la lista de usuarios y el código de estado HTTP 200 (OK).
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(serviceUser.getAllUser(), HttpStatus.OK);
    }

    /**
     * Obtiene un usuario específico según su identificador.
     * @param id Identificador del usuario a buscar.
     * @return ResponseEntity con el usuario si se encuentra, o código de estado HTTP 404 (NOT_FOUND) si no existe.
     */
    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        Optional<User> user = serviceUser.getUserById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina un usuario específico según su identificador.
     * @param id Identificador del usuario a eliminar.
     * @return ResponseEntity con el código de estado HTTP 204 (NO_CONTENT) si la eliminación es exitosa,
     * o HTTP 404 (NOT_FOUND) si el usuario no existe.
     */
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable String id) {
        try {
            serviceUser.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
