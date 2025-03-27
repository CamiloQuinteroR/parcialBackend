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




    // Básicos del crud
    public Product createProduct(Product product){
        repoProduct.save(product);
        return product;
    }   

    public List<Product> getAllProduct(){
        return repoProduct.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return repoProduct.findById(id);
    }

    public void deleteProductById(String id){
        Optional<Product> ProductOptional = getProductById(id);
        if (ProductOptional.isPresent()) {
            repoProduct.delete(ProductOptional.get());
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
    }

    public void deleteProducts() {
        repoProduct.deleteAll();
    }

    

}
