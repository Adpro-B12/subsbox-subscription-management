package id.ac.ui.cs.advprog.subscriptionmanagement.controller;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionmanagement.service.SubscriptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SubscriptionControllerTest {
    @Mock
    private SubscriptionService subscriptionService;

    @InjectMocks
    private SubscriptionController subscriptionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllSubscriptionBoxes() {
        SubscriptionBox box1 = new SubscriptionBox("Box1", "Monthly", 50, 1L);
        SubscriptionBox box2 = new SubscriptionBox("Box2", "Quarterly", 100, 2L);
        List<SubscriptionBox> boxes = Arrays.asList(box1, box2);

        when(subscriptionService.getAllBoxes()).thenReturn(boxes);

        ResponseEntity<List<SubscriptionBox>> response = subscriptionController.getAllSubscriptionBoxes();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("Box1", response.getBody().get(0).getName());
        assertEquals("Box2", response.getBody().get(1).getName());
    }

    @Test
    void testGetSubscriptionBox() {
        SubscriptionBox box = new SubscriptionBox("Box1", "Monthly", 50, 1L);
        when(subscriptionService.findBoxById(1L)).thenReturn(box);

        ResponseEntity<SubscriptionBox> response = subscriptionController.getSubscriptionBox(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Box1", response.getBody().getName());
    }

    @Test
    void testGetSubscriptionBoxNotFound() {
        when(subscriptionService.findBoxById(1L)).thenReturn(null);

        ResponseEntity<SubscriptionBox> response = subscriptionController.getSubscriptionBox(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetFilteredSubscriptionBoxesByPrice() {
        SubscriptionBox box = new SubscriptionBox("Box1", "Monthly", 50, 1L);
        List<SubscriptionBox> boxes = Collections.singletonList(box);

        when(subscriptionService.getFilteredBoxesByPrice(40, 60)).thenReturn(boxes);

        ResponseEntity<List<SubscriptionBox>> response = subscriptionController.getFilteredSubscriptionBoxesByPrice(40, 60);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Box1", response.getBody().get(0).getName());
    }

    @Test
    void testGetFilteredSubscriptionBoxesByName() {
        SubscriptionBox box = new SubscriptionBox("Box1", "Monthly", 50, 1L);
        List<SubscriptionBox> boxes = Collections.singletonList(box);

        when(subscriptionService.getFilteredBoxesByName("Box")).thenReturn(boxes);

        ResponseEntity<List<SubscriptionBox>> response = subscriptionController.getFilteredSubscriptionBoxesByName("Box");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testSubscribe() {
        Subscription subscription = new Subscription("testUser", 1L, 1L);
        when(subscriptionService.createSubscription(1L, "testUser")).thenReturn(subscription);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "testUser");

        ResponseEntity<Subscription> response = subscriptionController.subscribe(1L, requestBody);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("testUser", response.getBody().getUsername());
    }

    @Test
    void testSubscribeBadRequest() {
        when(subscriptionService.createSubscription(1L, "testUser")).thenThrow(new RuntimeException());

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "testUser");

        ResponseEntity<Subscription> response = subscriptionController.subscribe(1L, requestBody);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testCancelSubscription() {
        Subscription subscription = new Subscription("testUser", 1L, 1L);
        when(subscriptionService.cancelSubscription(1L)).thenReturn(subscription);

        ResponseEntity<Subscription> response = subscriptionController.cancelSubscription(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("testUser", response.getBody().getUsername());
    }

    @Test
    void testCancelSubscriptionBadRequest() {
        when(subscriptionService.cancelSubscription(1L)).thenThrow(new RuntimeException());

        ResponseEntity<Subscription> response = subscriptionController.cancelSubscription(1L);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testGetAllSubscriptions() {
        Subscription subscription = new Subscription("testUser", 1L, 1L);
        List<Subscription> subscriptions = Collections.singletonList(subscription);
        when(subscriptionService.getAllSubscriptions()).thenReturn(subscriptions);

        ResponseEntity<List<Subscription>> response = subscriptionController.getAllSubscriptions();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetUserSubscriptions() {
        Subscription subscription = new Subscription("testUser", 1L, 1L);
        List<Subscription> subscriptions = Collections.singletonList(subscription);
        when(subscriptionService.getFilteredSubscriptionsByUsername("testUser")).thenReturn(subscriptions);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "testUser");

        ResponseEntity<List<Subscription>> response = subscriptionController.getUserSubscriptions(requestBody);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetUserSubscriptionsNoContent() {
        when(subscriptionService.getFilteredSubscriptionsByUsername("testUser")).thenReturn(Collections.emptyList());

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "testUser");

        ResponseEntity<List<Subscription>> response = subscriptionController.getUserSubscriptions(requestBody);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testGetSubscriptionByStatus() {
        Subscription subscription = new Subscription("testUser", 1L, 1L);
        List<Subscription> subscriptions = Collections.singletonList(subscription);
        when(subscriptionService.getFilteredSubscriptionsByStatus("Subscribed")).thenReturn(subscriptions);

        ResponseEntity<List<Subscription>> response = subscriptionController.getSubscriptionByStatus("Subscribed");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetSubscriptionByStatusNoContent() {
        when(subscriptionService.getFilteredSubscriptionsByStatus("Subscribed")).thenReturn(Collections.emptyList());

        ResponseEntity<List<Subscription>> response = subscriptionController.getSubscriptionByStatus("Subscribed");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testSetSubscriptionStatus() {
        Subscription subscription = new Subscription("testUser", 1L, 1L);
        subscription.setStatus("Approved");
        when(subscriptionService.setSubscriptionStatus(1L, "Approved")).thenReturn(subscription);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("status", "Approved");
        requestBody.put("role", "ADMIN");

        ResponseEntity<Subscription> response = subscriptionController.setSubscriptionStatus(1L, requestBody);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Approved", response.getBody().getStatus());
    }

    @Test
    void testSetSubscriptionStatusBadRequest() {
        when(subscriptionService.setSubscriptionStatus(1L, "Approved")).thenThrow(new RuntimeException());

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("status", "Approved");
        requestBody.put("role", "ADMIN");

        ResponseEntity<Subscription> response = subscriptionController.setSubscriptionStatus(1L, requestBody);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
