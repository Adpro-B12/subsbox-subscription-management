package id.ac.ui.cs.advprog.subscriptionmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionBoxRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;

public class SubscriptionImplTest {

    @Mock
    private SubscriptionBoxRepository subscriptionBoxRepository;

    @InjectMocks
    private SubscriptionImpl subscriptionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBoxesReturnsAllBoxes() {
        SubscriptionBox box1 = new SubscriptionBox(); // Setup test data
        SubscriptionBox box2 = new SubscriptionBox();
        List<SubscriptionBox> mockBoxes = Arrays.asList(box1, box2);

        when(subscriptionBoxRepository.findAll()).thenReturn(mockBoxes);

        List<SubscriptionBox> boxes = subscriptionService.getAllBoxes();

        assertEquals(2, boxes.size());
        verify(subscriptionBoxRepository).findAll();
    }

    @Test
    public void testGetFilteredBoxesByPriceReturnsFilteredBoxes() {
        SubscriptionBox box1 = new SubscriptionBox(); // Assume setters to set prices
        box1.setPrice(100);
        SubscriptionBox box2 = new SubscriptionBox();
        box2.setPrice(200);
        List<SubscriptionBox> mockBoxes = Arrays.asList(box1, box2);

        when(subscriptionBoxRepository.findByPriceBetween(100, 200)).thenReturn(mockBoxes);

        List<SubscriptionBox> filteredBoxes = subscriptionService.getFilteredBoxesByPrice(100, 200);

        assertEquals(2, filteredBoxes.size());
        verify(subscriptionBoxRepository).findByPriceBetween(100, 200);
    }
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
//
//}
