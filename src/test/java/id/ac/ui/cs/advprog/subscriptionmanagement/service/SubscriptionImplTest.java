package id.ac.ui.cs.advprog.subscriptionmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionBoxRepository;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionRepository;

public class SubscriptionImplTest {

    // private MockMvc mockMvc;

    @Mock
    private SubscriptionRepository subscriptionRepository;
    @Mock
    private SubscriptionBoxRepository subscriptionBoxRepository;

    // @Mock
    // private SubscriptionBuilder subscriptionBuilder;

    @InjectMocks
    private SubscriptionImpl subscriptionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetAllBoxesReturnsAllBoxes() {
        SubscriptionBox box1 = new SubscriptionBox(); // Setup test data
        SubscriptionBox box2 = new SubscriptionBox();
        List<SubscriptionBox> mockBoxes = Arrays.asList(box1, box2);

        when(subscriptionBoxRepository.findAll()).thenReturn(mockBoxes);

        CompletableFuture<List<SubscriptionBox>> boxesFuture = subscriptionService.getAllBoxesAsync();

        List<SubscriptionBox> boxes = boxesFuture.join(); // Wait for the CompletableFuture to complete

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

        CompletableFuture<List<SubscriptionBox>> filteredBoxesFuture = subscriptionService.getFilteredBoxesByPriceAsync(100, 200);

        List<SubscriptionBox> filteredBoxes = filteredBoxesFuture.join(); // Wait for the CompletableFuture to complete

        assertEquals(2, filteredBoxes.size());
        verify(subscriptionBoxRepository).findByPriceBetween(100, 200);
    }

    @Test
    public void testCreateSubscription() {
        Long boxId = 1L;
        String buyerUsername = "user123";

        Subscription mockedSubscription = new Subscription();
        SubscriptionBox subscriptionBox = new SubscriptionBox();
        subscriptionBox.setId(boxId);
        subscriptionBox.setType("MONTHLY");
        when(subscriptionBoxRepository.findById(boxId)).thenReturn(Optional.of(subscriptionBox));
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(mockedSubscription);

        CompletableFuture<Subscription> resultFuture = subscriptionService.createSubscriptionAsync(boxId, buyerUsername);

        Subscription result = resultFuture.join();

        verify(subscriptionRepository).save(any(Subscription.class));
        assertNotNull(result);
    }

    @Test
    public void testCancelSubscription() {
        // Setup
        String uniqueCode = "MTH-12345";
        Subscription mockSubscription = new Subscription();
        mockSubscription.setStatus("ACTIVE");  // Initial status

        // Define behavior of the mocked repository
        when(subscriptionRepository.findByUniqueCode(uniqueCode)).thenReturn(mockSubscription);

        // Execute
        CompletableFuture<Subscription> resultFuture = subscriptionService.cancelSubscriptionAsync(uniqueCode);

        Subscription result = resultFuture.join();

        // Verify
        assertNotNull(result);
        assertEquals("CANCELLED", result.getStatus());
        verify(subscriptionRepository).findByUniqueCode(uniqueCode);  // Verify that the repository was called correctly
    }

    @Test
    public void testGetFilteredSubscriptionsByUsername() {
        // Setup
        String buyerUsername = "user123";
        List<Subscription> expectedSubscriptions = Arrays.asList(
                new Subscription(), // Add more properties as needed
                new Subscription()  // Add more properties as needed
        );

        // Define behavior of the mocked repository
        when(subscriptionRepository.findByUsername(buyerUsername)).thenReturn(expectedSubscriptions);

        // Execute
        CompletableFuture<List<Subscription>> resultFuture = subscriptionService.getFilteredSubscriptionsByUsernameAsync(buyerUsername);

        List<Subscription> result = resultFuture.join();

        // Verify
        assertNotNull(result);
        assertEquals(expectedSubscriptions.size(), result.size());
        assertEquals(expectedSubscriptions, result);
        verify(subscriptionRepository).findByUsername(buyerUsername);
    }

    @Test
    public void testGetFilteredSubscriptionsByStatus() {
        // Setup
        String status = "ACTIVE";
        List<Subscription> expectedSubscriptions = Arrays.asList(
                new Subscription(), // Add more properties as needed
                new Subscription()  // Add more properties as needed
        );

        // Define behavior of the mocked repository
        when(subscriptionRepository.findByStatus(status)).thenReturn(expectedSubscriptions);

        // Execute
        CompletableFuture<List<Subscription>> resultFuture = subscriptionService.getFilteredSubscriptionsByStatusAsync(status);

        List<Subscription> result = resultFuture.join();

        // Verify
        assertNotNull(result);
        assertEquals(expectedSubscriptions.size(), result.size());
        assertEquals(expectedSubscriptions, result);
        verify(subscriptionRepository).findByStatus(status);
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
