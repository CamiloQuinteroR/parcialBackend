package parcial.parcial.model;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Data
@Document(collection = "product")
public class Product {
    @Id
    private String id;
    private String nombre;
    private int precio;
    private String descripcion;

    public Product(String id, String nombre, int precio, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }
}
