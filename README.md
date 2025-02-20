**Rayienda Hasmaradana Najlamahsa - 2306172735**

<details>
<summary>Module 1</summary>

## Reflection 1
In Exercise 1, I implemented edit and delete product features. In the edit feature, when the user clicks the Edit button, a form is displayed with the product's current details using Thymeleaf, and when the user submits the form, the product then will later gets updated.
For the delete feature, when user clicks the delete button, the `ProductController.java` calls the service to remove the product. 

### Applied Clean Code Principles:
1. **Single Responsibility Principle (SRP)**: The `ProductController` delegates business logic to `ProductService`, ensuring separation of concerns.
2. **Descriptive Naming**: Variables and methods use clear, meaningful names that indicate their purpose.
3. **Avoiding Code Duplication**: The service layer is used to avoid repeating logic in controllers.
4. **Consistent Formatting**: Proper indentation and spacing are maintained for readability.

### Applied Secure Coding Practices:
1. **Input Validation**: Product updates and deletions are only processed if valid `productId` values are provided.
2. **Preventing Null Pointer Exceptions**: Null-safe comparisons and proper checks before accessing object properties.
3. **Encapsulation**: Product fields are private, and access is managed through getters and setters.
4. **Avoiding Hardcoded Values**: UUIDs are generated dynamically instead of using fixed values.

### Areas for Improvement:
1. **Dependency Injection Improvement**: Use constructor injection instead of field injection in `ProductController` to enhance testability.
2. **Thread Safety in Repository**: The `ProductRepository` should be synchronized if accessed concurrently.
3. **Improved Error Handling**: Return meaningful error messages when updating or deleting non-existent products.
4. **Logging Mechanism**: Implement logging in service methods to track product modifications.

By implementing these improvements, the code will be more maintainable, scalable, and secure.

## Reflection 2
### Unit Testing and Code Coverage:
After writing the unit test, I feel more confident that the implemented features function correctly. Writing unit tests ensures that each component of the code behaves as expected. However, determining the right number of unit tests in a class depends on the complexity of the logic. A good practice is to cover all possible paths, including positive and negative test cases.

To ensure sufficient test coverage, we can use code coverage metrics, which measure the percentage of code executed during testing. However, 100% code coverage does not guarantee bug-free software—it only means all lines were executed at least once. Edge cases and logical flaws might still exist, which is why functional and integration tests are equally important.

### Clean Code Issues in Functional Testing
In the case of CreateProductFunctionalTest.java, if a new functional test suite is added with the same setup procedures and instance variables, it might introduce code duplication. This could negatively impact code maintainability and readability.

### Potential Issues and Improvements
1. Code Duplication: Repeating setup code in multiple test classes leads to maintenance challenges.

- Solution: Extract the common setup logic into a base test class that other test classes can extend.

2. Violation of DRY Principle (Don’t Repeat Yourself): Writing redundant test logic increases the risk of inconsistency.
- Solution: Use a test utility class or parameterized tests where applicable.

3. Test Readability & Organization: Having similar test logic in multiple places may reduce readability.
- Solution: Group tests logically and follow naming conventions that describe the test's intent.
By refactoring the functional test suites to follow these principles, the test code will be cleaner, easier to maintain, and more scalable.

</details>

<details>
<summary>Module 2</summary>

## Reflection 2
1. **List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.**

    Issue: The import "import java.util.UUID;" was present in ProductControllerTest.java but was not being used.
    Strategy: Deleted the unused import to maintain clean, readable, and efficient code.


2. **Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment?**
   
    The CI workflows automate the project's build process, execute unit tests, and conduct code quality and security analysis whenever code is pushed or a pull request is created.
   The deployment workflow automatically builds a Docker image and deploys it to Koyeb upon pushes to the main branch.
   Additionally, scheduled checks and branch protection mechanisms enhance the reliability and security of the integration and deployment processes. While the implementation meets CI/CD standards, further improvements, such as integration testing and multi-environment deployments, could strengthen the pipeline even more.
</details>
