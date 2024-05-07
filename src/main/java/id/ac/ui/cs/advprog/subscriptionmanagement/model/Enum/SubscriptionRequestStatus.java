package id.ac.ui.cs.advprog.subscriptionmanagement.model.Enum;

import lombok.Getter;

@Getter
public enum SubscriptionRequestStatus {
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected");

    private final String status;

    private SubscriptionRequestStatus(String status) {
        this.status = status;
    }

    public static boolean contains(String status) {
        for (SubscriptionRequestStatus subscriptionRequestStatus : SubscriptionRequestStatus.values()) {
            if (subscriptionRequestStatus.name().equals(status)) {
                return true;
            }
        }
        return false;
    }
}
