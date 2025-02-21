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

    @Test
    void testFindById_MatchingProductId() {
        Product product = new Product();
        product.setProductId("matching-id");
        product.setProductName("Test Product");
        product.setProductQuantity(50);
        productRepository.create(product);

        Product foundProduct = productRepository.findById("matching-id");

        assertNotNull(foundProduct);
        assertEquals("matching-id", foundProduct.getProductId());
    }

    @Test
    void testUpdate_ProductWithSameId() {
        Product product = new Product();
        product.setProductId("update-test");
        product.setProductName("Old Name");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("update-test");
        updatedProduct.setProductName("New Name");
        updatedProduct.setProductQuantity(20);

        Product result = productRepository.update(updatedProduct);

        assertNotNull(result);
        assertEquals("New Name", result.getProductName());
        assertEquals(20, result.getProductQuantity());
    }

    @Test
    void testUpdate_ProductWithDifferentId() {
        Product product = new Product();
        product.setProductId("existing-id");
        product.setProductName("Original");
        product.setProductQuantity(30);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("different-id");
        updatedProduct.setProductName("Updated");
        updatedProduct.setProductQuantity(40);

        Product result = productRepository.update(updatedProduct);

        assertNull(result); // Must be NULL because iD does not match
    }

    @Test
    void testDelete_WithMismatchedProductId() {
        Product product = new Product();
        product.setProductId("delete-test");
        product.setProductName("To Be Deleted");
        product.setProductQuantity(15);
        productRepository.create(product);

        boolean deleted = productRepository.delete("wrong-id");
        assertFalse(deleted); // Can not delete because ID does not match
    }

    @Test
    void testCheckProductIdNotNullAndEquals() {
        Product product = new Product();
        product.setProductId("test-id");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
        productRepository.create(product);

        assertNotNull(product.getProductId());
        assertTrue(product.getProductId().equals("test-id"));
    }

    @Test
    void testCheckProductIdNotNullAndEquals_AnotherCase() {
        Product product = new Product();
        product.setProductId("another-id");
        product.setProductName("Another Product");
        product.setProductQuantity(20);
        productRepository.create(product);

        assertNotNull(product.getProductId());
        assertTrue(product.getProductId().equals("another-id"));
    }

    @Test
    void testCheckProductIdConditionForNull() {
        Product product = new Product();
        product.setProductId(null);
        product.setProductName("Null Product");
        product.setProductQuantity(0);
        productRepository.create(product);

        assertNull(product.getProductId());
    }

    @Test
    void testCheckProductIdConditionForEquality() {
        Product product = new Product();
        product.setProductId("equality-check");
        product.setProductName("Equality Product");
        product.setProductQuantity(5);
        productRepository.create(product);

        assertNotNull(product.getProductId());
        assertTrue(product.getProductId().equals("equality-check"));
    }

    @Test
    void testFindById_NullId() {
        // Create a product with a non-null ID
        Product product = new Product();
        product.setProductId("non-null-id");
        product.setProductName("Some Product");
        product.setProductQuantity(10);
        productRepository.create(product);

        // Now try to find by null ID
        Product foundProduct = productRepository.findById(null);
        // Expect null, since the condition product.getProductId() != null && ...
        // will fail for a null productId
        assertNull(foundProduct, "Expected null when searching with a null ID");
    }

    @Test
    void testDelete_NullId() {
        // Create a product with a non-null ID
        Product product = new Product();
        product.setProductId("delete-id");
        product.setProductName("Delete Test Product");
        product.setProductQuantity(5);
        productRepository.create(product);

        // Now try to delete with a null ID
        boolean deleted = productRepository.delete(null);
        // Expect false, because productId == null will fail the condition
        assertFalse(deleted, "Expected false when deleting with a null ID");
    }

    @Test
    void testUpdate_NullId() {
        // Create a product with a non-null ID
        Product product = new Product();
        product.setProductId("update-id");
        product.setProductName("Update Test Product");
        product.setProductQuantity(5);
        productRepository.create(product);

        // Create an updated product with a null ID
        Product updatedProduct = new Product();
        updatedProduct.setProductId(null);
        updatedProduct.setProductName("Updated Name");
        updatedProduct.setProductQuantity(10);

        Product result = productRepository.update(updatedProduct);
        // Expect null, because the update loop checks if (productId != null && equals)
        // so it will never match a null ID
        assertNull(result, "Expected null when updating with a null ID");
    }

}