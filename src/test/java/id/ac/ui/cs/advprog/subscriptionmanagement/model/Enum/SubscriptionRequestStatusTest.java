package id.ac.ui.cs.advprog.subscriptionmanagement.model.Enum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionRequestStatusTest {
    @Test
    public void testEnumValues() {
        assertEquals("Pending", SubscriptionRequestStatus.PENDING.getStatus());
        assertEquals("Approved", SubscriptionRequestStatus.APPROVED.getStatus());
        assertEquals("Rejected", SubscriptionRequestStatus.REJECTED.getStatus());
    }

    @Test
    public void testContains() {
        assertTrue(SubscriptionRequestStatus.contains("PENDING"));
        assertTrue(SubscriptionRequestStatus.contains("APPROVED"));
        assertTrue(SubscriptionRequestStatus.contains("REJECTED"));

        assertFalse(SubscriptionRequestStatus.contains("pending"));
        assertFalse(SubscriptionRequestStatus.contains("approved"));
        assertFalse(SubscriptionRequestStatus.contains("rejected"));

        assertFalse(SubscriptionRequestStatus.contains("Unknown"));
    }

    @Test
    public void testEnumIntegrity() {
        SubscriptionRequestStatus[] statuses = SubscriptionRequestStatus.values();
        assertEquals(3, statuses.length);
        assertEquals(SubscriptionRequestStatus.PENDING, statuses[0]);
        assertEquals(SubscriptionRequestStatus.APPROVED, statuses[1]);
        assertEquals(SubscriptionRequestStatus.REJECTED, statuses[2]);
    }
}
