package id.ac.ui.cs.advprog.subscriptionmanagement.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionBoxTest {
    @Test
    public void testSubscriptionBoxDefaultConstructor() {
        SubscriptionBox box = new SubscriptionBox();
        assertNull(box.getName());
        assertNull(box.getType());
        assertEquals(0, box.getPrice());
        assertNull(box.getItems());
    }

    @Test
    public void testSubscriptionBoxParameterizedConstructor() {
        SubscriptionBox box = new SubscriptionBox("Test Box", "Monthly", 100, 1L);
        assertEquals("Test Box", box.getName());
        assertEquals("Monthly", box.getType());
        assertEquals(100, box.getPrice());
        assertEquals(1L, box.getId());
    }

    @Test
    public void testSubscriptionBoxSettersAndGetters() {
        SubscriptionBox box = new SubscriptionBox();
        box.setName("Test Box");
        box.setType("Monthly");
        box.setPrice(100);
        List<Item> items = new ArrayList<>();
        box.setItems(items);

        assertEquals("Test Box", box.getName());
        assertEquals("Monthly", box.getType());
        assertEquals(100, box.getPrice());
        assertEquals(items, box.getItems());
    }
}
