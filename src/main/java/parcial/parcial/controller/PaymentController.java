package parcial.parcial.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import parcial.parcial.model.Payment;
import parcial.parcial.service.ServicePayment;

/**
 * Controlador REST para la gestión de pagos.
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private ServicePayment servicePayment;

    /**
     * Endpoint para crear un nuevo pago.
     * @param payment Objeto Payment recibido en el cuerpo de la solicitud.
     * @return ResponseEntity con el pago creado y el estado HTTP 201 (CREATED).
     */
    @PostMapping("/create")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        return new ResponseEntity<>(servicePayment.createPayment(payment), HttpStatus.CREATED);
    }

    /**
     * Endpoint para obtener todos los pagos registrados.
     * @return ResponseEntity con la lista de pagos y el estado HTTP 200 (OK).
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Payment>> getAllPayments() {
        return new ResponseEntity<>(servicePayment.getAllPayment(), HttpStatus.OK);
    }

    /**
     * Endpoint para obtener un pago específico mediante su ID.
     * @param id Identificador único del pago.
     * @return ResponseEntity con el pago encontrado y el estado HTTP 202 (ACCEPTED),
     *         o estado HTTP 404 (NOT FOUND) si no se encuentra.
     */
    @GetMapping("/getPayment/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable String id) {
        Optional<Payment> payment = servicePayment.getPaymentById(id);
        if (payment.isPresent()) {
            return new ResponseEntity<>(payment.get(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint para eliminar un pago específico mediante su ID.
     * @param id Identificador único del pago a eliminar.
     * @return ResponseEntity con estado HTTP 204 (NO CONTENT) si la eliminación es exitosa,
     *         o estado HTTP 404 (NOT FOUND) en caso de error.
     */
    @DeleteMapping("/deletePayment/{id}")
    public ResponseEntity<?> deletePaymentById(@PathVariable String id) {
        try {
            servicePayment.deletePaymentById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint para eliminar todos los pagos registrados.
     * @return ResponseEntity con estado HTTP 204 (NO CONTENT) después de eliminar todos los pagos.
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllPayments() {
        servicePayment.deletePayments();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
