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

   Issue: The import "import java.util.UUID;" an "import org.springframework.ui.Model;" was present in ProductControllerTest.java but was not being used.
   Strategy: Deleted the unused import to maintain clean, readable, and efficient code.


2. **Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment?**

   The CI workflows automate the project's build process, execute unit tests, and conduct code quality and security analysis whenever code is pushed or a pull request is created.
   The deployment workflow automatically builds a Docker image and deploys it to Koyeb upon pushes to the main branch.
   Additionally, scheduled checks and branch protection mechanisms enhance the reliability and security of the integration and deployment processes. While the implementation meets CI/CD standards, further improvements, such as integration testing and multi-environment deployments, could strengthen the pipeline even more.
</details>


<details>
<summary>Module 3</summary>

1. To keep things organized, I put CarController and ProductController in separate files, following the Single Responsibility Principle (SRP), each one focuses only on its own job. For flexibility, the Open-Closed Principle (OCP) is applied by using interfaces for CarService and ProductService, so new features can be added without touching the existing code. The Dependency Inversion Principle (DIP) makes testing and updates easier by ensuring controllers rely on interfaces instead of being tied to specific service implementations.

2. This matters to keep the project maintainable, testable, and scalable. With SRP, tweaking car-related features won’t accidentally break product functionality. OCP means we can extend the system without messing with what’s already working, reducing bugs. DIP lets us swap out service implementations effortlessly, making unit testing a breeze with mock data instead of real database calls.

3. Without these principles, the code would get messy. If cars and products were managed in one controller (SRP violation), changing one thing could break another. Ignoring OCP would mean rewriting old code for every new feature, increasing the risk of unexpected issues. And without DIP, testing would be difficult since the controllers would be stuck with specific services.

</details>

<details>
<summary>Module 4</summary>

## Reflection 4

1. **Is the TDD process effective in ensuring reliable code development?**  
   Yes, in my experience following the TDD workflow helped in structuring the implementation process efficiently. By starting with tests, I was able to focus on expected behavior before writing the actual implementation. It ensured that every change was backed by a clear test case, reducing unexpected regressions. However, one challenge I faced was defining tests before fully understanding all the edge cases, which sometimes required refactoring the test cases themselves. While the current TDD approach provided structure, I realized that some tests could be optimized for better readability and maintainability. Specifically:
    - Improving test descriptions to better document intent.
    - Refining assertions to make failure messages clearer.
    - Introducing parameterized tests for repetitive test scenarios.

   These changes would enhance both the efficiency and clarity of the test suite.

2. **F.I.R.S.T. Principle Reflection**

The F.I.R.S.T. principle is essential in evaluating the effectiveness of unit tests. Below is my reflection on how well the tests adhered to these principles:

| Principle   | Evaluation |
|------------|------------|
| **Fast** | The tests executed quickly since they primarily involved in-memory operations and mocks, avoiding heavy dependencies like databases. |
| **Independent** | Each test was self-contained and did not depend on external state. Mocks were used to ensure isolation between test cases. |
| **Repeatable** | Tests produced consistent results across multiple runs, ensuring deterministic behavior. |
| **Self-Validating** | Each test had clear assertions that automatically determined success or failure, eliminating the need for manual inspection. |
| **Timely** |  Some tests were written after partial implementation instead of strictly following TDD. I need to improve adherence to the **Red-Green-Refactor** cycle by ensuring all failing tests are written before implementation. |

**Next Steps for Improvement**
- **Enhance edge case coverage**: Expanding test cases for edge scenarios such as invalid inputs and boundary conditions.
- **Introduce integration tests**: While unit tests ensure individual components work as expected, integration tests will help validate interactions between components.
- **Optimize test structure**: Implementing better test naming conventions and structuring test classes for maintainability.

By refining these aspects, I can further improve test effectiveness and maintainability in future development cycles.

</details>