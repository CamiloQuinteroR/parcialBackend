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

    // Metodo para crear un nuevo usuario y guardarlo en la base de datos
    public User createUser(User user) {
        repoUser.save(user);
        return user;
    }

    // Metodo para obtener la lista de todos los usuarios almacenados en la base de datos
    public List<User> getAllUser() {
        return repoUser.findAll();
    }

    // Metodo para obtener un usuario específico por su ID
    public Optional<User> getUserById(String id) {
        return repoUser.findById(id);
    }

    // Metodo para eliminar un usuario por su ID, verificando primero si existe
    public void deleteUserById(String id) {
        Optional<User> userOptional = getUserById(id);
        if (userOptional.isPresent()) {
            repoUser.delete(userOptional.get());
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }
}
