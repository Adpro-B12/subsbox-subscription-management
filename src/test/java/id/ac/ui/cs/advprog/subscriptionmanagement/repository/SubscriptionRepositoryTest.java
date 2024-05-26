package id.ac.ui.cs.advprog.subscriptionmanagement.repository;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SubscriptionRepositoryTest {
    @Autowired
    private SubscriptionRepository repository;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
        Subscription subscription = new Subscription("testUser", 1L, 1L);
        subscription.setUniqueCode("MTH-12345");
        subscription.setStatus("PENDING");
        repository.save(subscription);
    }

    @Test
    void testFindByUniqueCode() {
        Subscription result = repository.findByUniqueCode("MTH-12345");
        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
    }

    @Test
    void testFindByUsername() {
        List<Subscription> result = repository.findByUsername("testUser");
        assertEquals(1, result.size());
        assertEquals("testUser", result.get(0).getUsername());
    }

    @Test
    void testFindByStatus() {
        List<Subscription> result = repository.findByStatus("PENDING");
        assertEquals(1, result.size());
        assertEquals("PENDING", result.get(0).getStatus());
    }
}
