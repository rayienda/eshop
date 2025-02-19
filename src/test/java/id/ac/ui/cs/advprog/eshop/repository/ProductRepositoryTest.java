package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindById_ProductFound() {
        Product product = new Product();
        product.setProductId("123");
        productRepository.create(product);

        Product foundProduct = productRepository.findById("123");

        assertNotNull(foundProduct);
        assertEquals("123", foundProduct.getProductId());
    }

    @Test
    void testFindById_ProductNotFound() {
        Product result = productRepository.findById("non-existent-id");
        assertNull(result); // Should return null when no product is found
    }

    @Test
    void testUpdate_ProductFound() {
        Product product = new Product();
        product.setProductId("product-1");
        product.setProductName("Soap");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("product-1");
        updatedProduct.setProductName("Sabun");
        updatedProduct.setProductQuantity(34);

        Product result = productRepository.update(updatedProduct);

        assertNotNull(result);
        assertEquals("Sabun", result.getProductName());
        assertEquals(34, result.getProductQuantity());
    }

    @Test
    void testUpdate_ProductNotFound() {  // ✅ Only one instance of this method exists now
        Product updatedProduct = new Product();
        updatedProduct.setProductId("non-existent-id");

        Product result = productRepository.update(updatedProduct);
        assertNull(result);
    }

    @Test
    void testDelete_ProductFound() {
        Product product = new Product();
        product.setProductId("product-1");
        product.setProductName("Sampo Cap Ben");
        product.setProductQuantity(100);
        productRepository.create(product);

        boolean deleted = productRepository.delete("product-1");
        assertTrue(deleted);
    }

    @Test
    void testDelete_ProductNotFound() {
        boolean deleted = productRepository.delete("non-existent-id");
        assertFalse(deleted);
    }
}
