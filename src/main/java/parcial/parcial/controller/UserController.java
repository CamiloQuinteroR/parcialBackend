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

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(serviceUser.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(serviceUser.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        Optional<User> user = serviceUser.getUserById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

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