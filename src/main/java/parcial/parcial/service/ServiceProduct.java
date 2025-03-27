package parcial.parcial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import parcial.parcial.model.Product;
import parcial.parcial.repository.RepoProduct;

@Service
public class ServiceProduct {
    @Autowired
    private RepoProduct repoProduct;

    // Metodo para crear un nuevo producto y guardarlo en la base de datos
    public Product createProduct(Product product) {
        repoProduct.save(product);
        return product;
    }   

    // Metodo para obtener la lista de todos los productos almacenados en la base de datos
    public List<Product> getAllProduct() {
        return repoProduct.findAll();
    }

    // Metodo para obtener un producto específico por su ID
    public Optional<Product> getProductById(String id) {
        return repoProduct.findById(id);
    }

    // Metodo para eliminar un producto por su ID, verificando primero si existe
    public void deleteProductById(String id) {
        Optional<Product> productOptional = getProductById(id);
        if (productOptional.isPresent()) {
            repoProduct.delete(productOptional.get());
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
    }

    // Metodo para eliminar todos los productos de la base de datos
    public void deleteProducts() {
        repoProduct.deleteAll();
    }
}
