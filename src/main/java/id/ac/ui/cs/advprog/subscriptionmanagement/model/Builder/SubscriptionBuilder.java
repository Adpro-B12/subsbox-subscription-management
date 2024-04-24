package id.ac.ui.cs.advprog.subscriptionmanagement.model.Builder;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Enum.SubscriptionStatus;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;

import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalTime;

import java.util.UUID;

@Component
public class SubscriptionBuilder {
    private Subscription currentSubscription;

    public SubscriptionBuilder(){
        this.reset();
    }

    public SubscriptionBuilder reset() {
        currentSubscription = new Subscription();
        // firstSetUp();
        return this;
    }

    // public SubscriptionBuilder firstSetUp() {
    //     currentSubscription.setId(UUID.randomUUID());
    //     currentSubscription.setPaymentStatus(SubscriptionStatus.WAITING_RESPONSE.getStatus());
       
    //     return this;
    // }

    // public SubscriptionBuilder addPaymentStatus(String status) {
    //     if (SubscriptionStatus.contains(status)) {
    //         currentSubscription.setPaymentStatus(status);
    //         return this;
    //     }
    //     throw new IllegalArgumentException();
    // }

    // public SubscriptionBuilder addPaymentAmount(int paymentAmount) {
    //     currentSubscription.setPaymentAmount(paymentAmount);
    //     return this;
    // }

    // public SubscriptionBuilder addPaymentResponseTime(long paymentResponseTime) {
    //     currentSubscription.setPaymentResponseTime(paymentResponseTime);
    //     return this;
    // }

    // public SubscriptionBuilder addBuyerUsername(String buyerUsername) {
    //     currentSubscription.setBuyerUsername(buyerUsername);
    //     return this;
    // }

    // public Subscription build() {
    //     return currentSubscription;
    // }

    // public SubscriptionBuilder setCurrent(Subscription Subscription) {
    //     currentSubscription = Subscription;
    //     return this;
    // }
}
