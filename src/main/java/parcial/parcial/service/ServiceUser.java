package parcial.parcial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parcial.parcial.model.User;
import parcial.parcial.repository.RepoUser;


@Service
public class ServiceUser {
    @Autowired
    private RepoUser repoUser;


    // Básicos del crud
    public User createUser(User user){
        repoUser.save(user);
        return user;
    }

    public List<User> getAllUser(){
        return repoUser.findAll();
    }

    public Optional<User> getUserById(String id) {
        return repoUser.findById(id);
    }

    public void deleteUserById(String id) {
        Optional<User> userOptional = getUserById(id);
        if (userOptional.isPresent()) {
            repoUser.delete(userOptional.get());
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }


}
