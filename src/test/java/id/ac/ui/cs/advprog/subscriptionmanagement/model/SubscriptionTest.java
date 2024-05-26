package id.ac.ui.cs.advprog.subscriptionmanagement.model;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Enum.SubscriptionRequestStatus;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.Enum.SubscriptionStatus;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.Enum.SubscriptionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionTest {
    @Test
    void testSubscriptionDefaultConstructor() {
        Subscription subscription = new Subscription();
        assertNull(subscription.getUsername());
        assertNull(subscription.getUniqueCode());
    }

    @Test
    void testSubscriptionParameterizedConstructor() {
        Subscription subscription = new Subscription("testUser", 1L, 1L);
        assertEquals("testUser", subscription.getUsername());
        assertEquals(1L, subscription.getSubscriptionBoxId());
        assertEquals(1L, subscription.getId());
    }

    @Test
    void testSubscriptionSettersAndGetters() {
        Subscription subscription = new Subscription();
        subscription.setUsername("testUser");
        subscription.setSubscriptionBoxId(1L);
        subscription.setUniqueCode("MTH-12345");
        subscription.setStatus("PENDING");
        Date startDate = new Date();
        subscription.setStartDate(startDate);
        Date endDate = new Date();
        subscription.setEndDate(endDate);

        assertEquals("testUser", subscription.getUsername());
        assertEquals(1L, subscription.getSubscriptionBoxId());
        assertEquals("MTH-12345", subscription.getUniqueCode());
        assertEquals("PENDING", subscription.getStatus());
        assertEquals(startDate, subscription.getStartDate());
        assertEquals(endDate, subscription.getEndDate());
    }
}