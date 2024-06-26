package id.ac.ui.cs.advprog.subscriptionmanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.*;
import id.ac.ui.cs.advprog.subscriptionmanagement.handler.ResourceNotFoundException;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.Builder.SubscriptionBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

class SubscriptionImplTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;
    @Mock
    private SubscriptionBoxRepository subscriptionBoxRepository;

    @Mock
    private SubscriptionBuilder subscriptionBuilder;

    @InjectMocks
    private SubscriptionImpl subscriptionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBoxes() {
        List<SubscriptionBox> boxes = List.of(new SubscriptionBox("Test Box", "Monthly", 100, 1L));
        when(subscriptionBoxRepository.findAll()).thenReturn(boxes);

        List<SubscriptionBox> result = subscriptionService.getAllBoxes();
        assertEquals(1, result.size());
        assertEquals("Test Box", result.get(0).getName());
    }

    @Test
    void testGetFilteredBoxesByPrice() {
        List<SubscriptionBox> boxes = List.of(new SubscriptionBox("Box1", "Monthly", 50, 1L));
        when(subscriptionBoxRepository.findByPriceBetween(40, 100)).thenReturn(boxes);

        List<SubscriptionBox> result = subscriptionService.getFilteredBoxesByPrice(40, 100);
        assertEquals(1, result.size());
        assertEquals("Box1", result.get(0).getName());
    }

    @Test
    void testGetFilteredBoxesByName() {
        List<SubscriptionBox> boxes = List.of(new SubscriptionBox("Test Box", "Monthly", 100, 1L));
        when(subscriptionBoxRepository.findByNameContaining("Test")).thenReturn(boxes);

        List<SubscriptionBox> result = subscriptionService.getFilteredBoxesByName("Test");
        assertEquals(1, result.size());
        assertEquals("Test Box", result.get(0).getName());
    }

    @Test
    void testFindBoxById() throws ResourceNotFoundException {
        SubscriptionBox box = new SubscriptionBox("Test Box", "Monthly", 100, 1L);
        when(subscriptionBoxRepository.findById(1L)).thenReturn(Optional.of(box));

        SubscriptionBox result = subscriptionService.findBoxById(1L);
        assertEquals("Test Box", result.getName());
    }

    @Test
    void testFindBoxByIdNotFound() {
        when(subscriptionBoxRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> subscriptionService.findBoxById(1L));
    }

    @Test
    void testCreateSubscription() {
        SubscriptionBox box = new SubscriptionBox("Test Box", "Monthly", 100, 1L);
        when(subscriptionBoxRepository.findById(1L)).thenReturn(Optional.of(box));
        Subscription subscription = new Subscription("testUser", 1L, 1L);
        when(subscriptionBuilder.reset()).thenReturn(subscriptionBuilder);
        when(subscriptionBuilder.addIdBox(1L)).thenReturn(subscriptionBuilder);
        when(subscriptionBuilder.addUniqueCode("Monthly")).thenReturn(subscriptionBuilder); // Update to match the new method signature
        when(subscriptionBuilder.addBuyerUsername("testUser")).thenReturn(subscriptionBuilder);
        when(subscriptionBuilder.build()).thenReturn(subscription);
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        Subscription result = subscriptionService.createSubscription(1L, "Monthly", "testUser"); // Update to match the new method signature
        assertEquals("testUser", result.getUsername());
    }

    @Test
    void testCancelSubscription() {
        Subscription subscription = new Subscription("testUser", 1L, 1L);
        subscription.setStatus("PENDING");
        when(subscriptionRepository.findById(1L)).thenReturn(Optional.of(subscription));
        when(subscriptionRepository.save(subscription)).thenReturn(subscription);

        Subscription result = subscriptionService.cancelSubscription(1L);
        assertEquals("CANCELLED", result.getStatus());
    }

    @Test
    void testSetSubscriptionStatus() {
        Subscription subscription = new Subscription("testUser", 1L, 1L);
        subscription.setStatus("PENDING");
        when(subscriptionRepository.findById(1L)).thenReturn(Optional.of(subscription));
        when(subscriptionRepository.save(subscription)).thenReturn(subscription);

        Subscription result = subscriptionService.setSubscriptionStatus(1L, "APPROVED");
        assertEquals("APPROVED", result.getStatus());
    }

    @Test
    void testGetAllSubscriptions() {
        List<Subscription> subscriptions = List.of(new Subscription("testUser", 1L, 1L));
        when(subscriptionRepository.findAll()).thenReturn(subscriptions);

        List<Subscription> result = subscriptionService.getAllSubscriptions();

        assertEquals(1, result.size());
        assertEquals("testUser", result.get(0).getUsername());
    }

    @Test
    void testGetFilteredSubscriptionsByUsername() {
        List<Subscription> subscriptions = List.of(new Subscription("testUser", 1L, 1L));
        when(subscriptionRepository.findByUsername("testUser")).thenReturn(subscriptions);

        List<Subscription> result = subscriptionService.getFilteredSubscriptionsByUsername("testUser");

        assertEquals(1, result.size());
        assertEquals("testUser", result.get(0).getUsername());
    }

    @Test
    void testGetFilteredSubscriptionsByStatus() {
        List<Subscription> subscriptions = List.of(new Subscription("testUser", 1L, 1L));
        when(subscriptionRepository.findByStatus("PENDING")).thenReturn(subscriptions);

        List<Subscription> result = subscriptionService.getFilteredSubscriptionsByStatus("PENDING");

        assertEquals(1, result.size());
        assertEquals("testUser", result.get(0).getUsername());
    }
}
