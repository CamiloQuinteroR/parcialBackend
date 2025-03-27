package parcial.parcial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import parcial.parcial.model.Payment;
import parcial.parcial.repository.RepoPayment;

/**
 * Servicio para la gestión de pagos en el sistema.
 */
@Service
public class ServicePayment {

    @Autowired
    private RepoPayment repoPayment;

    /**
     * Crea un nuevo pago y lo almacena en la base de datos.
     * @param payment Objeto Payment con los datos del pago.
     * @return El pago creado.
     */
    public Payment createPayment(Payment payment) {
        repoPayment.save(payment);
        return payment;
    }

    /**
     * Obtiene la lista de todos los pagos almacenados en la base de datos.
     * @return Lista de pagos.
     */
    public List<Payment> getAllPayment() {
        return repoPayment.findAll();
    }

    /**
     * Busca un pago por su identificador.
     * @param id Identificador único del pago.
     * @return Un Optional que contiene el pago si se encuentra, o vacío si no existe.
     */
    public Optional<Payment> getPaymentById(String id) {
        return repoPayment.findById(id);
    }

    /**
     * Elimina un pago de la base de datos según su identificador.
     * @param id Identificador único del pago a eliminar.
     * @throws RuntimeException si el pago no se encuentra.
     */
    public void deletePaymentById(String id) {
        Optional<Payment> PaymentOptional = getPaymentById(id);
        if (PaymentOptional.isPresent()) {
            repoPayment.delete(PaymentOptional.get());
        } else {
            throw new RuntimeException("Payment no encontrado con ID: " + id);
        }
    }

    /**
     * Elimina todos los pagos almacenados en la base de datos.
     */
    public void deletePayments() {
        repoPayment.deleteAll();
    }
}
