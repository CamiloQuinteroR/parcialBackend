package parcial.parcial.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "payment")
public class Payment {
    @Id
    private String idPago;
    private String idUsuario;
    private String idProduct;
    private String fechaPago;
    private int montoTotal;
    private String estado;


    public Payment(String idPago, String idUsuario, String idProduct, String fechaPago, int montoTotal, String estado) {
        this.idPago = idPago;
        this.idUsuario = idUsuario;
        this.idProduct = idProduct;
        this.fechaPago = fechaPago;
        this.montoTotal = montoTotal;
        this.estado = estado;
    }

    public String getIdPago() {
        return idPago;
    }
}
