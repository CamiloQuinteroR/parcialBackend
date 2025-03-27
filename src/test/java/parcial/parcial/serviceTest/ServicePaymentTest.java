package parcial.parcial.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import parcial.parcial.model.Payment;
import parcial.parcial.repository.RepoPayment;
import parcial.parcial.service.ServicePayment;

public class ServicePaymentTest {

    @Mock
    private RepoPayment repoPayment;

    @InjectMocks
    private ServicePayment servicePayment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreatePayment() {
        Payment payment = new Payment("1", "u1", "p1", "2024-03-27", 100, "Aceptado");
        when(repoPayment.save(payment)).thenReturn(payment);

        Payment createdPayment = servicePayment.createPayment(payment);

        assertNotNull(createdPayment);
        assertEquals("1", createdPayment.getIdPago());
    }

    @Test
    void shouldGetAllPayments() {
        List<Payment> payments = Arrays.asList(
                new Payment("1", "u1", "p1", "2024-03-27", 100, "Aceptado"),
                new Payment("2", "u2", "p2", "2024-03-28", 200, "Aceptado"));

        when(repoPayment.findAll()).thenReturn(payments);

        List<Payment> result = servicePayment.getAllPayment();

        assertEquals(2, result.size());
    }

    @Test
    void shouldGetPaymentById() {
        Payment payment = new Payment("1", "u1", "p1", "2024-03-27", 100, "Aceptado");
        when(repoPayment.findById("1")).thenReturn(Optional.of(payment));

        Optional<Payment> foundPayment = servicePayment.getPaymentById("1");

        assertTrue(foundPayment.isPresent());
        assertEquals("1", foundPayment.get().getIdPago());
    }

    @Test
    void shouldDeletePaymentById() {
        Payment payment = new Payment("1", "u1", "p1", "2024-03-27", 100, "Aceptado");
        when(repoPayment.findById("1")).thenReturn(Optional.of(payment));
        doNothing().when(repoPayment).delete(payment);

        assertDoesNotThrow(() -> servicePayment.deletePaymentById("1"));
        verify(repoPayment, times(1)).delete(payment);
    }

    @Test
    void shouldDeletePaymentByIdNotFound() {
        when(repoPayment.findById("2")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            servicePayment.deletePaymentById("2");
        });

        assertEquals("Payment no encontrado con ID: 2", exception.getMessage());
    }
}
