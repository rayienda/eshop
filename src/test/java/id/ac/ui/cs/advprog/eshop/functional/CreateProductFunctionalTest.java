package id.ac.ui.cs.advprog.eshop.functional;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createProductTest(ChromeDriver driver) {
        // navigate to Create Product
        driver.get(baseUrl + "/product/create");

        // fill in the product name field
        WebElement nameField = driver.findElement(By.name("productName"));
        nameField.sendKeys("Test Product");

        // fill in the product quantity field
        WebElement quantityField = driver.findElement(By.name("productQuantity"));
        quantityField.sendKeys("42");

        // click the submit button to create the product
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        // redirect to the product list page
        WebElement newProduct = driver.findElement(By.xpath("//*[contains(text(), 'Test Product')]"));
        assertTrue(newProduct.isDisplayed(), "The new product should be visible in the product list");
    }
}