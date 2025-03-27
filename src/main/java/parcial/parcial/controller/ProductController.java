package parcial.parcial.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import parcial.parcial.model.Product;
import parcial.parcial.service.ServiceProduct;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ServiceProduct serviceProduct;

    /**
     * Crea un nuevo producto en la base de datos.
     * @param product Objeto de tipo Product que se debe almacenar.
     * @return ResponseEntity con el producto creado y el código de estado HTTP 201 (CREATED).
     */
    @PutMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(serviceProduct.createProduct(product), HttpStatus.CREATED);
    }

    /**
     * Obtiene la lista de todos los productos almacenados.
     * @return ResponseEntity con la lista de productos y el código de estado HTTP 202 (ACCEPTED).
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProduct() {
        return new ResponseEntity<>(serviceProduct.getAllProduct(), HttpStatus.ACCEPTED);
    }

    /**
     * Obtiene un producto específico según su identificador.
     * @param id Identificador del producto a buscar.
     * @return ResponseEntity con el producto si se encuentra, o código de estado HTTP 404 (NOT_FOUND) si no existe.
     */
    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        Optional<Product> product = serviceProduct.getProductById(id);
        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina todos los productos almacenados en la base de datos.
     * @return ResponseEntity con el código de estado HTTP 202 (ACCEPTED).
     */
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteProduct() {
        serviceProduct.deleteProducts();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * Elimina un producto específico según su identificador.
     * @param id Identificador del producto a eliminar.
     * @return ResponseEntity con el código de estado HTTP 202 (ACCEPTED) si la eliminación es exitosa,
     * o HTTP 404 (NOT_FOUND) si el producto no existe.
     */
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable String id) {
        try {
            serviceProduct.deleteProductById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
