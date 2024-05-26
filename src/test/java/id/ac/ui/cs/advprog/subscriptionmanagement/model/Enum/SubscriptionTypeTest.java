package id.ac.ui.cs.advprog.subscriptionmanagement.model.Enum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionTypeTest {
    @Test
    public void testSubscriptionTypeCodes() {
        assertEquals("MTH", SubscriptionType.MONTHLY.getCode());
        assertEquals("QTR", SubscriptionType.QUARTERLY.getCode());
        assertEquals("SAA", SubscriptionType.SEMI_ANNUAL.getCode());
    }

    @Test
    public void testContainsValidCode() {
        assertTrue(SubscriptionType.contains("MONTHLY"));
        assertTrue(SubscriptionType.contains("QUARTERLY"));
        assertTrue(SubscriptionType.contains("SEMI_ANNUAL"));
    }

    @Test
    public void testContainsInvalidCode() {
        assertFalse(SubscriptionType.contains("YEARLY"));
        assertFalse(SubscriptionType.contains("WEEKLY"));
        assertFalse(SubscriptionType.contains("DAILY"));
    }

    @Test
    public void testContainsNullCode() {
        assertFalse(SubscriptionType.contains(null));
    }

    @Test
    public void testContainsEmptyCode() {
        assertFalse(SubscriptionType.contains(""));
    }
}
