package parcial.parcial.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import parcial.parcial.model.Payment;
import parcial.parcial.repository.RepoPayment;

@Service
public class ServicePayment {
    @Autowired
    private RepoPayment repoPayment;
    

    // Básicos del crud
    public Payment createPayment(Payment payment){
        
        repoPayment.save(payment);
        return payment;
    }   
    
    public List<Payment> getAllPayment(){
        return repoPayment.findAll();
    }

    public Optional<Payment> getPaymentById(String id) {
        return repoPayment.findById(id);
    }

    public void deletePaymentById(String id) {
        Optional<Payment> PaymentOptional = getPaymentById(id);
        if (PaymentOptional.isPresent()) {
            repoPayment.delete(PaymentOptional.get());
        } else {
            throw new RuntimeException("Payment no encontrado con ID: " + id);
        }
    }

    
    public void deletePayments() {
        repoPayment.deleteAll();
    }




}
