package parcial.parcial.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String documento;
    private String nombre;
    private String email;
    private String telefono;

    public User(String id, String documento, String nombre, String email, String telefono) {
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }
}


