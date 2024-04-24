package id.ac.ui.cs.advprog.subscriptionmanagement.model.Builder;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Enum.SubscriptionStatus;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;

import org.springframework.stereotype.Component;


import java.util.UUID;

@Component
public class SubscriptionBuilder {
    private Subscription currentSubscription;

    public SubscriptionBuilder(){
        this.reset();
    }

    public SubscriptionBuilder reset() {
        currentSubscription = new Subscription();
        firstSetUp();
        return this;
    }

    public SubscriptionBuilder firstSetUp() {
        UUID tempId = UUID.randomUUID();
        currentSubscription.setId(tempId);
        currentSubscription.setStatus(SubscriptionStatus.PENDING.getStatus());

        String subscriptionBoxType = currentSubscription.getSubscriptionBox().getType();
        if (subscriptionBoxType.equals("MONTHLY")){
            currentSubscription.setUniqueCode("MTH"+'-'+tempId.toString());
        } else if (subscriptionBoxType.equals("YEARLY")){
            currentSubscription.setUniqueCode("QTR"+'-'+tempId.toString());
        } else {
            currentSubscription.setUniqueCode("SAA"+'-'+tempId.toString());
        }
       
        return this;
    }

    public SubscriptionBuilder addStatus(String status) {
        if (SubscriptionStatus.contains(status)) {
            currentSubscription.setStatus(status);
            return this;
        }
        throw new IllegalArgumentException();
    }


    // public SubscriptionBuilder addBuyerUsername(String buyerUsername) {
    //     currentSubscription.setBuyerUsername(buyerUsername);
    //     return this;
    // }

    public Subscription build() {
        return currentSubscription;
    }

    public SubscriptionBuilder setCurrent(Subscription Subscription) {
        currentSubscription = Subscription;
        return this;
    }
}
