package id.ac.ui.cs.advprog.subscriptionmanagement.model.Enum;

import lombok.Getter;

@Getter
public enum SubscriptionType {
    MONTHLY("MTH"),
    QUARTERLY("QTR"),
    SEMI_ANNUAL("SAA");

    private final String code;

    private SubscriptionType(String code) {
        this.code = code;
    }

    public static boolean contains(String code) {
        for (SubscriptionType subscriptionType : SubscriptionType.values()) {
            if (subscriptionType.name().equals(code)) {
                return true;
            }
        }
        return false;
    }
}
