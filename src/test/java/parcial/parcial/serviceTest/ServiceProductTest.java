package parcial.parcial.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import parcial.parcial.model.Product;
import parcial.parcial.repository.RepoProduct;
import parcial.parcial.service.ServiceProduct;

public class ServiceProductTest {

    @InjectMocks
    private ServiceProduct serviceProduct;

    @Mock
    private RepoProduct repoProduct;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product1 = new Product("1", "p1", 100, "dp1");
        product2 = new Product("2", "p2", 200, "Descripción B");
    }

    @Test
    void shouldCreateProduct() {
        when(repoProduct.save(any(Product.class))).thenReturn(product1);

        Product result = serviceProduct.createProduct(product1);

        assertNotNull(result);
        assertEquals("p1", result.getNombre());
        verify(repoProduct, times(1)).save(product1);
    }

    @Test
    void shouldGetAllProduct() {
        when(repoProduct.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> result = serviceProduct.getAllProduct();

        assertEquals(2, result.size());
        verify(repoProduct, times(1)).findAll();
    }

    @Test
    void shouldGetProductById() {
        when(repoProduct.findById("1")).thenReturn(Optional.of(product1));

        Optional<Product> result = serviceProduct.getProductById("1");

        assertTrue(result.isPresent());
        assertEquals("p1", result.get().getNombre());
        verify(repoProduct, times(1)).findById("1");
    }

    @Test
    void shouldDeleteProductByIdSuccess() {
        when(repoProduct.findById("1")).thenReturn(Optional.of(product1));

        serviceProduct.deleteProductById("1");

        verify(repoProduct, times(1)).delete(product1);
    }

    @Test
    void shouldDeleteProductByIdNotFound() {
        when(repoProduct.findById("1")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            serviceProduct.deleteProductById("1");
        });

        assertEquals("Producto no encontrado con ID: 1", exception.getMessage());
        verify(repoProduct, never()).delete(any(Product.class));
    }

    @Test
    void shouldDeleteProducts() {
        serviceProduct.deleteProducts();
        verify(repoProduct, times(1)).deleteAll();
    }
}
