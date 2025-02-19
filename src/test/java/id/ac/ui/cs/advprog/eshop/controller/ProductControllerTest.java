package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);
        assertEquals("CreateProduct", viewName);
        verify(model, times(1)).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String viewName = productController.createProductPost(product, model);
        assertEquals("redirect:list", viewName);
        verify(productService, times(1)).create(any(Product.class));
    }

    @Test
    void testProductListPage() {
        List<Product> products = new ArrayList<>();
        when(productService.findAll()).thenReturn(products);

        String viewName = productController.productListPage(model);
        assertEquals("productList", viewName);
        verify(model, times(1)).addAttribute("products", products);
    }

    @Test
    void testEditProductPage() {
        Product product = new Product();
        product.setProductId("1");
        List<Product> products = List.of(product);
        when(productService.findAll()).thenReturn(products);

        String viewName = productController.editProductPage("1", model);
        assertEquals("EditProduct", viewName);
        verify(model, times(1)).addAttribute("product", product);
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        String viewName = productController.editProductPost(product);
        assertEquals("redirect:/product/list", viewName);
        verify(productService, times(1)).update(product);
    }

    @Test
    void testDeleteProduct() {
        String viewName = productController.deleteProduct("1");
        assertEquals("redirect:/product/list", viewName);
        verify(productService, times(1)).delete("1");
    }

    @Test
    void testEditProductPage_ProductFound() {
        Product product = new Product();
        product.setProductId("1");
        when(productService.findAll()).thenReturn(List.of(product));

        String viewName = productController.editProductPage("1", model);

        assertEquals("EditProduct", viewName);
        verify(model, times(1)).addAttribute("product", product);
    }



    @Test
    void testEditProductPage_ProductNotFound() {
        when(productService.findAll()).thenReturn(new ArrayList<>()); // Empty product list

        String viewName = productController.editProductPage("999", model);

        assertEquals("redirect:/product/list", viewName); // Expect redirect when product is not found
    }

}
