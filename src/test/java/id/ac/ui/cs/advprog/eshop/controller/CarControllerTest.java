package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

    @Mock
    private CarService carService;

    @Mock
    private Model model;

    @InjectMocks
    private CarController carController;

    @Test
    public void testCreateCarPage() {
        String viewName = carController.createCarPage(model);
        verify(model, times(1)).addAttribute(eq("car"), any(Car.class));
        assertEquals("createCar", viewName);
    }

    @Test
    public void testCarListPage() {
        List<Car> cars = Arrays.asList(new Car(), new Car());
        when(carService.findAll()).thenReturn(cars);

        String viewName = carController.carListPage(model);

        verify(model, times(1)).addAttribute("cars", cars);
        assertEquals("carList", viewName);
    }

    @Test
    public void testEditCarPage() {
        Car car = new Car();
        when(carService.findById("123")).thenReturn(car);

        String viewName = carController.editCarPage("123", model);

        verify(model, times(1)).addAttribute("car", car);
        assertEquals("editCar", viewName);
    }
}
