package parcial.parcial.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import parcial.parcial.model.Payment;
import parcial.parcial.service.ServicePayment;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private ServicePayment servicePayment;

    @PostMapping("/create")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        return new ResponseEntity<>(servicePayment.createPayment(payment), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Payment>> getAllPayments() {
        return new ResponseEntity<>(servicePayment.getAllPayment(), HttpStatus.OK);
    }

    @GetMapping("/getPayment/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable String id) {
        Optional<Payment> payment = servicePayment.getPaymentById(id);
        if (payment.isPresent()) {
            return new ResponseEntity<>(payment.get(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletePayment/{id}")
    public ResponseEntity<?> deletePaymentById(@PathVariable String id) {
        try {
            servicePayment.deletePaymentById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllPayments() {
        servicePayment.deletePayments();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
