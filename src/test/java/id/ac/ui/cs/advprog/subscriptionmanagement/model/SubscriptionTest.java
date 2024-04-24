package id.ac.ui.cs.advprog.subscriptionmanagement.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class SubscriptionTest {
    private Subscription subscription;
    private User user;
    private SubscriptionBox subscriptionBox;
    private Date startDate;
    private Date endDate;

    @BeforeEach
    public void setUp() {
        user = new User();
        subscriptionBox = new SubscriptionBox();
        startDate = new Date();
        endDate = new Date();
        subscription = new Subscription("Subscribed", startDate, endDate, user, subscriptionBox);
    }

    @Test
    public void testSubscriptionStatus() {
        assertEquals("Subscribed", subscription.getStatus());
    }

    @Test
    public void testSubscriptionDates() {
        assertEquals(startDate, subscription.getStartDate());
        assertEquals(endDate, subscription.getEndDate());
    }

    @Test
    public void testSubscriptionUser() {
        assertEquals(user, subscription.getUser());
    }

    @Test
    public void testSubscriptionBox() {
        assertEquals(subscriptionBox, subscription.getSubscriptionBox());
    }
}
