package id.ac.ui.cs.advprog.subscriptionmanagement.model.Enum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SubscriptionTypeTest {
    @Test
    void testSubscriptionTypeCodes() {
        assertEquals("MTH", SubscriptionType.MONTHLY.getCode());
        assertEquals("QTR", SubscriptionType.QUARTERLY.getCode());
        assertEquals("SAA", SubscriptionType.SEMI_ANNUAL.getCode());
    }

    @Test
    void testContainsValidCode() {
        assertTrue(SubscriptionType.contains("MONTHLY"));
        assertTrue(SubscriptionType.contains("QUARTERLY"));
        assertTrue(SubscriptionType.contains("SEMI_ANNUAL"));
    }

    @Test
    void testContainsInvalidCode() {
        assertFalse(SubscriptionType.contains("YEARLY"));
        assertFalse(SubscriptionType.contains("WEEKLY"));
        assertFalse(SubscriptionType.contains("DAILY"));
    }

    @Test
    void testContainsNullCode() {
        assertFalse(SubscriptionType.contains(null));
    }

    @Test
    void testContainsEmptyCode() {
        assertFalse(SubscriptionType.contains(""));
    }
}
