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

public class SubscriptionTest {
    private SubscriptionBox subscriptionBox;
    private Subscription subscription;

    @BeforeEach
    void setUp() {
        // Create a sample subscription box
        subscriptionBox = new SubscriptionBox();
        subscriptionBox.setId("1");
        subscriptionBox.setName("Beauty Box");
        subscriptionBox.setType(SubscriptionType.MONTHLY.getCode());
        subscriptionBox.setPrice(50);

        // Create sample items for the subscription box
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setId("1");
        item1.setName("Facial Cleanser");
        item1.setQuantity(1);
        items.add(item1);
        Item item2 = new Item();
        item2.setId("2");
        item2.setName("Moisturizer");
        item2.setQuantity(1);
        items.add(item2);
        subscriptionBox.setItems(items);

        // Create a sample subscription
        subscription = new Subscription();
        subscription.setId(UUID.randomUUID());
        subscription.setUniqueCode(SubscriptionType.MONTHLY.getCode() + "-" + UUID.randomUUID().toString().substring(0, 8));
        subscription.setStatus(SubscriptionStatus.SUBSCRIBED.getStatus());
        subscription.setStartDate(new Date());
        subscription.setEndDate(new Date());
        subscription.setSubscriptionBox(subscriptionBox);
    }

    @Test
    void testSubscriptionCreation() {
        assertNotNull(subscription);
        assertNotNull(subscription.getId());
        assertNotNull(subscription.getUniqueCode());
        assertNotNull(subscription.getStatus());
        assertNotNull(subscription.getStartDate());
        assertNotNull(subscription.getEndDate());
        assertNotNull(subscription.getSubscriptionBox());
    }

    @Test
    void testUniqueCodeFormat() {
        String uniqueCode = subscription.getUniqueCode();
        assertTrue(uniqueCode.startsWith(SubscriptionType.MONTHLY.getCode()));
    }

    @Test
    void testSubscriptionCancellation() {
        subscription.setStatus(SubscriptionStatus.CANCELLED.getStatus());
        assertEquals(SubscriptionStatus.CANCELLED.getStatus(), subscription.getStatus());
    }

    @Test
    void testSubscriptionHistoryFiltering() {
        // Assuming there's a method to filter subscription history by status
        List<Subscription> subscriptionHistory = new ArrayList<>();
        Subscription subscription1 = new Subscription();
        subscription1.setStatus(SubscriptionStatus.SUBSCRIBED.getStatus());
        subscriptionHistory.add(subscription1);
        Subscription subscription2 = new Subscription();
        subscription2.setStatus(SubscriptionStatus.CANCELLED.getStatus());
        subscriptionHistory.add(subscription2);
        Subscription subscription3 = new Subscription();
        subscription3.setStatus(SubscriptionStatus.PENDING.getStatus());
        subscriptionHistory.add(subscription3);

        // Filtering for subscribed subscriptions
        List<Subscription> subscribedSubscriptions = new ArrayList<>();
        for (Subscription sub : subscriptionHistory) {
            if (sub.getStatus().equals(SubscriptionStatus.SUBSCRIBED.getStatus())) {
                subscribedSubscriptions.add(sub);
            }
        }

        assertEquals(1, subscribedSubscriptions.size());
    }

    @Test
    void testSubscriptionApproval() {
        // Assuming there's a method for admin to approve subscription requests
        subscription.setStatus(SubscriptionRequestStatus.PENDING.getStatus());
        assertEquals(SubscriptionRequestStatus.PENDING.getStatus(), subscription.getStatus());
        // Admin approves the subscription
        subscription.setStatus(SubscriptionRequestStatus.APPROVED.getStatus());
        assertEquals(SubscriptionRequestStatus.APPROVED.getStatus(), subscription.getStatus());
    }

    @Test
    void testSubscriptionRejection() {
        // Assuming there's a method for admin to reject subscription requests
        subscription.setStatus(SubscriptionRequestStatus.PENDING.getStatus());
        assertEquals(SubscriptionRequestStatus.PENDING.getStatus(), subscription.getStatus());
        // Admin rejects the subscription
        subscription.setStatus(SubscriptionRequestStatus.REJECTED.getStatus());
        assertEquals(SubscriptionRequestStatus.REJECTED.getStatus(), subscription.getStatus());
    }
}