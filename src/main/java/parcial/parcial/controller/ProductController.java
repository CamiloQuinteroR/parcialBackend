package parcial.parcial.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PutMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return new ResponseEntity<>(serviceProduct.createProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(serviceProduct.getAllProduct(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id){
        Optional<Product> product = serviceProduct.getProductById(id);
        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteProduct(){
        serviceProduct.deleteProducts();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable String id){
        try {
            serviceProduct.deleteProductById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
