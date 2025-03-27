package parcial.parcial.controllerTest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import parcial.parcial.controller.UserController;
import parcial.parcial.model.User;
import parcial.parcial.service.ServiceUser;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean  
    private ServiceUser serviceUser;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void shouldGetUserByIdNotFound() throws Exception {
        when(serviceUser.getUserById("1")).thenReturn(Optional.empty());

        mockMvc.perform(get("/user/getUser/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteUserById() throws Exception {
        doNothing().when(serviceUser).deleteUserById("1");

        mockMvc.perform(delete("/user/deleteUser/1"))
                .andExpect(status().isNoContent());

        verify(serviceUser, times(1)).deleteUserById("1");
    }
}

