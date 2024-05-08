package id.ac.ui.cs.advprog.subscriptionmanagement.model.Builder;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Enum.SubscriptionStatus;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import org.springframework.stereotype.Component;
import java.util.Calendar;
import java.util.Date;

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

        currentSubscription.setStatus(SubscriptionStatus.PENDING.getStatus());

        Date startDate = new Date(); 
        currentSubscription.setStartDate(startDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        SubscriptionBox subscriptionBox = currentSubscription.getSubscriptionBox();

        if(subscriptionBox == null) {
            return null;
        }

        String subscriptionBoxType = subscriptionBox.getType();
        
        if (subscriptionBoxType.equals("MONTHLY")) {
            calendar.add(Calendar.MONTH, 1);
            currentSubscription.setUniqueCode("MTH"+'-'+tempId.toString());
        } else if (subscriptionBoxType.equals("QUARTERLY")) {
            calendar.add(Calendar.MONTH, 3);
            currentSubscription.setUniqueCode("QTR"+'-'+tempId.toString());
        } else if(subscriptionBoxType.equals("SEMIANNUAL")) {
            calendar.add(Calendar.MONTH, 6);
            currentSubscription.setUniqueCode("SAA"+'-'+tempId.toString());
        }
        Date endDate = calendar.getTime();
        currentSubscription.setEndDate(endDate);
        
        
       
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
