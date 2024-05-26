package id.ac.ui.cs.advprog.subscriptionmanagement.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionBoxRepository;

@DataJpaTest
public class SubscriptionBoxRepositoryTest {

    @Autowired
    private SubscriptionBoxRepository repository;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
        SubscriptionBox box1 = new SubscriptionBox("Box1", "Monthly", 50, 1L);
        SubscriptionBox box2 = new SubscriptionBox("Box2", "Quarterly", 150, 2L);
        repository.save(box1);
        repository.save(box2);
//        subscriptionBoxRepository = new SubscriptionBoxRepository();
    }

    @Test
    public void testFindByPriceBetween() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<SubscriptionBox> result = repository.findByPriceBetween(40, 100, pageable);
        assertEquals(1, result.getTotalElements());
        assertEquals("Box1", result.getContent().get(0).getName());
    }

    @Test
    public void testFindByNameContaining() {
        List<SubscriptionBox> result = repository.findByNameContaining("Box");
        assertEquals(2, result.size());

        result = repository.findByNameContaining("1");
        assertEquals(1, result.size());
        assertEquals("Box1", result.get(0).getName());
    }

//    @Test
//    public void testAddBox() {
//        SubscriptionBox box = new SubscriptionBox();
//        SubscriptionBox addedBox = subscriptionBoxRepository.addBox(box);
//        assertNotNull(addedBox);
//    }
//
//    @Test
//    public void testDeleteBox() {
//        SubscriptionBox box = new SubscriptionBox();
//        box.setId("1");
//        subscriptionBoxRepository.addBox(box);
//        SubscriptionBox deletedBox = subscriptionBoxRepository.deleteBox(box.getId());
//        assertNotNull(deletedBox);
//    }
//
//    @Test
//    public void testEditBox() {
//        SubscriptionBox box = new SubscriptionBox();
//        box.setId("1");
//        String id = box.getId();
//        box.setName("abc");
//        subscriptionBoxRepository.addBox(box);
//        box.setName("def");
//        SubscriptionBox editedBox = subscriptionBoxRepository.editBox(id, box);
//        assertEquals("def", editedBox.getName());
//    }
//
//    @Test
//    public void testViewAll() {
//        SubscriptionBox box1 = new SubscriptionBox();
//        SubscriptionBox box2 = new SubscriptionBox();
//        subscriptionBoxRepository.addBox(box1);
//        subscriptionBoxRepository.addBox(box2);
//        List<SubscriptionBox> allBoxes = subscriptionBoxRepository.viewAll();
//        assertEquals(2, allBoxes.size());
//    }
//
//    @Test
//    public void testViewDetails() {
//        SubscriptionBox box = new SubscriptionBox();
//        box.setName("abc");
//        box.setId("1");
//        subscriptionBoxRepository.addBox(box);
//        String details = subscriptionBoxRepository.viewDetails(box.getId());
//        assertEquals("abc", details);
//    }
//
//    @Test
//    public void testFilterByPrice() {
//        SubscriptionBox box1 = new SubscriptionBox();
//        box1.setPrice(15);
//        SubscriptionBox box2 = new SubscriptionBox();
//        box2.setPrice(25);
//        subscriptionBoxRepository.addBox(box1);
//        subscriptionBoxRepository.addBox(box2);
//        List<SubscriptionBox> filteredBoxes = subscriptionBoxRepository.filterByPrice(15);
//        assertEquals(1, filteredBoxes.size());
//    }
}