package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService mockProductService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setControllerAdvice(new GlobalExceptionHandler()) // Exception handling for test environment
                .build();
    }

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProductPost() throws Exception {
        Product newProduct = new Product();
        newProduct.setProductId(UUID.randomUUID().toString());
        when(mockProductService.create(any(Product.class))).thenReturn(newProduct);

        mockMvc.perform(post("/product/create")
                        .flashAttr("product", newProduct))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(mockProductService, times(1)).create(any(Product.class));
    }

    @Test
    void testProductListPage() throws Exception {
        List<Product> sampleProductList = new ArrayList<>();
        sampleProductList.add(new Product());
        when(mockProductService.findAll()).thenReturn(sampleProductList);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ProductList"))
                .andExpect(model().attributeExists("products"));

        verify(mockProductService, times(1)).findAll();
    }

    @Test
    void testEditProductPageFound() throws Exception {
        Product existingProduct = new Product();
        existingProduct.setProductId("123");
        List<Product> existingProducts = new ArrayList<>();
        existingProducts.add(existingProduct);
        when(mockProductService.findAll()).thenReturn(existingProducts);

        mockMvc.perform(get("/product/edit/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testEditProductPageNotFound() throws Exception {
        when(mockProductService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/product/edit/999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    void testEditProductPageWithDifferentId() throws Exception {
        Product product1 = new Product();
        product1.setProductId("111");
        Product product2 = new Product();
        product2.setProductId("222");
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        when(mockProductService.findAll()).thenReturn(productList);

        mockMvc.perform(get("/product/edit/333"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    void testEditProductPost() throws Exception {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("123");

        mockMvc.perform(post("/product/edit")
                        .flashAttr("product", updatedProduct))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(mockProductService, times(1)).update(any(Product.class));
    }

    @Test
    void testEditProductPostFails() throws Exception {
        doThrow(new RuntimeException("Update failed")).when(mockProductService).update(any(Product.class));

        mockMvc.perform(post("/product/edit")
                        .flashAttr("product", new Product()))
                .andExpect(status().isInternalServerError()) // Expect HTTP 500
                .andExpect(content().string("Update failed")); // Ensure exception message is returned

        verify(mockProductService, times(1)).update(any(Product.class));
    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(get("/product/delete/123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(mockProductService, times(1)).delete(eq("123"));
    }

    @Test
    void testDeleteProductFails() throws Exception {
        doThrow(new RuntimeException("Delete failed")).when(mockProductService).delete(anyString());

        mockMvc.perform(get("/product/delete/123"))
                .andExpect(status().isInternalServerError()) // Expect HTTP 500
                .andExpect(content().string("Delete failed")); // Ensure exception message is returned

        verify(mockProductService, times(1)).delete(eq("123"));
    }

    @Test
    void testEditProductPageWithNullId() throws Exception {
        Product productWithNullId = new Product();
        productWithNullId.setProductId(null);
        List<Product> productList = new ArrayList<>();
        productList.add(productWithNullId);

        when(mockProductService.findAll()).thenReturn(productList);

        mockMvc.perform(get("/product/edit/123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }
}

/**
 * Exception Handler for tests to prevent ServletException issues.
 */
@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}