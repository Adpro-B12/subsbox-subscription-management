package id.ac.ui.cs.advprog.subscriptionmanagement.model.Enum;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum SubscriptionType {
    PENDING("PENDING"),
    SUBSCRIBED("SUBSCRIBED"),
    CANCELLED("CANCELLED");

    private final String status;

    private SubscriptionType(String type) {
        this.status = status;
    }

    public static boolean contains(String status) {
        for (SubscriptionStatus subscriptionStatus : SubscriptionStatus.values()) {
            if (subscriptionStatus.name().equals(status)) {
                return true;
            }
        }
        return false;
    }
}