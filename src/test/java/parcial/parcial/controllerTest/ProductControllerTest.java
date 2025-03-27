package parcial.parcial.controllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import parcial.parcial.controller.ProductController;
import parcial.parcial.model.Product;
import parcial.parcial.service.ServiceProduct;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ProductController productController;

    @Mock
    private ServiceProduct serviceProduct;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        product1 = new Product("1", "p1", 10023, "dp1");
        product2 = new Product("2", "p2", 20022, "dp2");
    }

    @Test
    void shouldCreateProduct() throws Exception {
        when(serviceProduct.createProduct(any(Product.class))).thenReturn(product1);

        mockMvc.perform(put("/product/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(product1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("p1"));

        verify(serviceProduct, times(1)).createProduct(any(Product.class));
    }

    @Test
    void shouldGetAllProduct() throws Exception {
        List<Product> products = Arrays.asList(product1, product2);
        when(serviceProduct.getAllProduct()).thenReturn(products);

        mockMvc.perform(get("/product/getAll"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.size()").value(2));

        verify(serviceProduct, times(1)).getAllProduct();
    }

    @Test
    void shouldGetProductByIdFound() throws Exception {
        when(serviceProduct.getProductById("1")).thenReturn(Optional.of(product1));

        mockMvc.perform(get("/product/getProduct/1"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.nombre").value("p1"));

        verify(serviceProduct, times(1)).getProductById("1");
    }

    @Test
    void shouldGetProductByIdNotFound() throws Exception {
        when(serviceProduct.getProductById("1")).thenReturn(Optional.empty());

        mockMvc.perform(get("/product/getProduct/1"))
                .andExpect(status().isNotFound());

        verify(serviceProduct, times(1)).getProductById("1");
    }

    @Test
    void shouldDeleteProductByIdSuccess() throws Exception {
        doNothing().when(serviceProduct).deleteProductById("1");

        mockMvc.perform(delete("/product/deleteProduct/1"))
                .andExpect(status().isAccepted());

        verify(serviceProduct, times(1)).deleteProductById("1");
    }

    @Test
    void shouldDeleteProductByIdNotFound() throws Exception {
        doThrow(new RuntimeException("Producto no encontrado con ID: 1")).when(serviceProduct).deleteProductById("1");

        mockMvc.perform(delete("/product/deleteProduct/1"))
                .andExpect(status().isNotFound());

        verify(serviceProduct, times(1)).deleteProductById("1");
    }

    @Test
    void shouldDeleteAllProducts() throws Exception {
        doNothing().when(serviceProduct).deleteProducts();

        mockMvc.perform(delete("/product/deleteAll"))
                .andExpect(status().isAccepted());

        verify(serviceProduct, times(1)).deleteProducts();
    }
}
