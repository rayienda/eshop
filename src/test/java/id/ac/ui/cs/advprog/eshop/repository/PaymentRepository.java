package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
    }

    @Test
    void testSaveAndFindById() {
        Payment payment = new Payment("1", "Voucher Code", "SUCCESS");

        paymentRepository.save(payment);
        Optional<Payment> retrievedPayment = paymentRepository.findById("1");


        assertTrue(retrievedPayment.isPresent());
        assertEquals("1", retrievedPayment.get().getId());
        assertEquals("Voucher Code", retrievedPayment.get().getMethod());
        assertEquals("SUCCESS", retrievedPayment.get().getStatus());
    }
    @Test
    void testFindById_NotFound() {
        Optional<Payment> payment = paymentRepository.findById("999");

        assertFalse(payment.isPresent());
    }

    @Test
    void testFindAll() {
        Payment payment1 = new Payment("1", "Voucher Code", "SUCCESS");
        Payment payment2 = new Payment("2", "Cash on Delivery", "REJECTED");

        paymentRepository.save(payment1);
        paymentRepository.save(payment2);
        List<Payment> payments = paymentRepository.findAll();

        assertEquals(2, payments.size());
        assertEquals("1", payments.get(0).getId());
        assertEquals("2", payments.get(1).getId());
    }

    @Test
    void testFindAll_EmptyRepository() {
        List<Payment> payments = paymentRepository.findAll();

        assertTrue(payments.isEmpty());
    }
}