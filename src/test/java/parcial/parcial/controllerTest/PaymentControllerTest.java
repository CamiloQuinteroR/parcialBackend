package parcial.parcial.controllerTest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.test.web.servlet.MvcResult;
import parcial.parcial.controller.PaymentController;
import parcial.parcial.model.Payment;
import parcial.parcial.service.ServicePayment;

@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicePayment servicePayment;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetPaymentByIdNotFound() throws Exception {
        when(servicePayment.getPaymentById("1")).thenReturn(Optional.empty());

        mockMvc.perform(get("/payment/getPayment/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeletePaymentById() throws Exception {
        doNothing().when(servicePayment).deletePaymentById("1");

        mockMvc.perform(delete("/payment/deletePayment/1"))
                .andExpect(status().isNoContent());

        verify(servicePayment, times(1)).deletePaymentById("1");
    }

    @Test
    public void shouldDeleteAllPayments() throws Exception {
        doNothing().when(servicePayment).deletePayments();

        mockMvc.perform(delete("/payment/deleteAll"))
                .andExpect(status().isNoContent());

        verify(servicePayment, times(1)).deletePayments();
    }
}
