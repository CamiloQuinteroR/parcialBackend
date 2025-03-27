package parcial.parcial.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import parcial.parcial.model.User;
import parcial.parcial.repository.RepoUser;
import parcial.parcial.service.ServiceUser;


@ExtendWith(MockitoExtension.class)
public class ServiceUserTest {

    @Mock
    private RepoUser repoUser;

    @InjectMocks
    private ServiceUser serviceUser;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("1", null, "u1", "u1.@mail.com", null);
    }

    @Test
    void shouldCreateUser() {
        when(repoUser.save(user)).thenReturn(user);
        User createdUser = serviceUser.createUser(user);
        assertNotNull(createdUser);
        assertEquals("u1", createdUser.getNombre());
        verify(repoUser, times(1)).save(user);
    }

    @Test
    void shouldGetAllUser() {
        List<User> users = Arrays.asList(user);
        when(repoUser.findAll()).thenReturn(users);

        List<User> result = serviceUser.getAllUser();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(repoUser, times(1)).findAll();
    }

    @Test
    void shouldGetUserByIdFound() {
        when(repoUser.findById("1")).thenReturn(Optional.of(user));
        Optional<User> result = serviceUser.getUserById("1");
        assertTrue(result.isPresent());
        assertEquals("u1", result.get().getNombre());
        verify(repoUser, times(1)).findById("1");
    }

    @Test
    void shouldGetUserByIdNotFound() {
        when(repoUser.findById("2")).thenReturn(Optional.empty());
        Optional<User> result = serviceUser.getUserById("2");
        assertFalse(result.isPresent());
        verify(repoUser, times(1)).findById("2");
    }

    @Test
    void shouldDeleteUserByIdFound() {
        when(repoUser.findById("1")).thenReturn(Optional.of(user));
        doNothing().when(repoUser).delete(user);

        serviceUser.deleteUserById("1");

        verify(repoUser, times(1)).delete(user);
    }

    @Test
    void shouldDeleteUserByIdNotFound() {
        when(repoUser.findById("2")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            serviceUser.deleteUserById("2");
        });

        assertEquals("Usuario no encontrado con ID: 2", exception.getMessage());
        verify(repoUser, never()).delete(any());
    }
}
