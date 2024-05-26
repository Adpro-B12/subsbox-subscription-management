package id.ac.ui.cs.advprog.subscriptionmanagement.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;

@DataJpaTest
class SubscriptionBoxRepositoryTest {

    @Autowired
    private SubscriptionBoxRepository repository;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
        SubscriptionBox box1 = new SubscriptionBox("Box1", "Monthly", 50, 1L);
        SubscriptionBox box2 = new SubscriptionBox("Box2", "Quarterly", 150, 2L);
        repository.save(box1);
        repository.save(box2);
    }

    @Test
    void testFindByPriceBetween() {
        List<SubscriptionBox> result = repository.findByPriceBetween(40, 100);
        assertEquals(1, result.size());
        assertEquals("Box1", result.get(0).getName());
    }

    @Test
    void testFindByNameContaining() {
        List<SubscriptionBox> result = repository.findByNameContaining("Box");
        assertEquals(2, result.size());

        result = repository.findByNameContaining("1");
        assertEquals(1, result.size());
        assertEquals("Box1", result.get(0).getName());
    }
}