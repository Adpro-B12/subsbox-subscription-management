package id.ac.ui.cs.advprog.subscriptionmanagement.service;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.*;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SubscriptionImplTest {

    @Mock
    private SubscriptionBoxRepository subscriptionBoxRepository;

    @InjectMocks
    private SubscriptionService subscriptionService;

    private SubscriptionBox box1, box2, box3;

//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        box1 = new SubscriptionBox(); // Assume SubscriptionBox has a constructor to set properties
        box1.setPrice(100);
        box2 = new SubscriptionBox();
        box2.setPrice(150);
        box3 = new SubscriptionBox();
        box3.setPrice(200);

        when(subscriptionBoxRepository.findAll()).thenReturn(Arrays.asList(box1, box2, box3));
        when(subscriptionBoxRepository.findByPriceRange(100, 150)).thenReturn(Arrays.asList(box1, box2));
    }

    @Test
    void testGetAllBoxes() {
        List<SubscriptionBox> boxes = subscriptionService.getAllBoxes();
        assertNotNull(boxes);
        assertEquals(3, boxes.size(), "Should retrieve all boxes");
        verify(subscriptionBoxRepository).findAll();
    }

    @Test
    void testGetFilteredBoxesByPrice() {
        List<SubscriptionBox> filteredBoxes = subscriptionService.getFilteredBoxesByPrice(100, 150);
        assertNotNull(filteredBoxes);
        assertEquals(2, filteredBoxes.size(), "Should retrieve boxes within the price range");
        assertTrue(filteredBoxes.contains(box1) && filteredBoxes.contains(box2), "Should only contain box1 and box2");
        verify(subscriptionBoxRepository).findByPriceRange(100, 150);
    }
//    @Test
//    public void testCreateSubscription() {
//        Subscription subscription = new Subscription();
//        when(subscriptionRepository.save(subscription)).thenReturn(subscription);
//
//        Subscription savedSubscription = subscriptionService.create(subscription);
//
//        assertNotNull(savedSubscription);
//        verify(subscriptionRepository, times(1)).save(subscription);
//    }
//
//    @Test
//    public void testFindAllSubscriptions() {
//        List<Subscription> subscriptions = new ArrayList<>();
//        when(subscriptionRepository.findAll()).thenReturn(subscriptions);
//
//        List<Subscription> foundSubscriptions = subscriptionService.findAll();
//
//        assertNotNull(foundSubscriptions);
//        assertEquals(subscriptions.size(), foundSubscriptions.size());
//        verify(subscriptionRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void testFindSubscriptionById() {
//        UUID subscriptionId = UUID.randomUUID();
//        Subscription subscription = new Subscription();
//        when(subscriptionRepository.findById(subscriptionId)).thenReturn(subscription);
//
//        Subscription foundSubscription = subscriptionService.findById(subscriptionId);
//
//        assertNotNull(foundSubscription);
//        verify(subscriptionRepository, times(1)).findById(subscriptionId);
//    }
//
//    @Test
//    public void testUpdateSubscription() {
//        Subscription subscription = new Subscription();
//        when(subscriptionRepository.save(subscription)).thenReturn(subscription);
//
//        Subscription updatedSubscription = subscriptionService.update(subscription);
//
//        assertNotNull(updatedSubscription);
//        verify(subscriptionRepository, times(1)).save(subscription);
//    }
//
//    @Test
//    public void testDeleteSubscriptionById() {
//        UUID subscriptionId = UUID.randomUUID();
//        Subscription subscription = new Subscription();
//        when(subscriptionRepository.deleteById(subscriptionId)).thenReturn(subscription);
//
//        Subscription deletedSubscription = subscriptionService.deleteSubscriptionById(subscriptionId);
//
//        assertNotNull(deletedSubscription);
//        verify(subscriptionRepository, times(1)).deleteById(subscriptionId);
//    }
//
//    @Test
//    public void testFindSubscriptionByNonExistentId() {
//        UUID subscriptionId = UUID.randomUUID();
//        when(subscriptionRepository.findById(subscriptionId)).thenReturn(null);
//
//        Subscription foundSubscription = subscriptionService.findById(subscriptionId);
//
//        assertNull(foundSubscription);
//    }
//
//    @Test
//    public void testUpdateNonExistentSubscription() {
//        Subscription subscription = new Subscription();
//        when(subscriptionRepository.save(subscription)).thenReturn(null);
//
//        Subscription updatedSubscription = subscriptionService.update(subscription);
//
//        assertNull(updatedSubscription);
//    }
//
//    @Test
//    public void testDeleteNonExistentSubscriptionById() {
//        UUID subscriptionId = UUID.randomUUID();
//        when(subscriptionRepository.deleteById(subscriptionId)).thenReturn(null);
//
//        Subscription deletedSubscription = subscriptionService.deleteSubscriptionById(subscriptionId);
//
//        assertNull(deletedSubscription);
//    }

}
