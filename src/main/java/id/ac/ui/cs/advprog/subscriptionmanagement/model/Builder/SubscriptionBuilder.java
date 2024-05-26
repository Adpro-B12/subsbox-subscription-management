package id.ac.ui.cs.advprog.subscriptionmanagement.model.Builder;


import id.ac.ui.cs.advprog.subscriptionmanagement.model.Enum.SubscriptionStatus;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Component
public class SubscriptionBuilder {
    private Subscription currentSubscription;


//    private SubscriptionBoxRepository subscriptionBoxRepository;

    public SubscriptionBuilder(){

        this.reset();
    }

    public SubscriptionBuilder reset() {
        currentSubscription = new Subscription();
        firstSetUp();
        return this;
    }

    public SubscriptionBuilder firstSetUp() {

        currentSubscription.setStatus(SubscriptionStatus.PENDING.getStatus());
        return this;
    }

    public SubscriptionBuilder addStatus(String status) {
        if (SubscriptionStatus.contains(status)) {
            currentSubscription.setStatus(status);
            return this;
        }
        throw new IllegalArgumentException();
    }

    public SubscriptionBuilder addIdBox(Long idBox) {
        currentSubscription.setSubscriptionBoxId(idBox);
        return this;
    }

    public SubscriptionBuilder addUniqueCode(SubscriptionBox subscriptionBox) {
        Date startDate = new Date();
        currentSubscription.setStartDate(startDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        String subscriptionBoxType = subscriptionBox.getType();
        String tempId = UUID.randomUUID().toString();
        if (subscriptionBoxType.equals("MTH")) {
            calendar.add(Calendar.MONTH, 1);
            currentSubscription.setUniqueCode("MTH"+'-'+tempId);
        } else if (subscriptionBoxType.equals("QTR")) {
            calendar.add(Calendar.MONTH, 3);
            currentSubscription.setUniqueCode("QTR"+'-'+tempId);
        } else if(subscriptionBoxType.equals("SAA")) {
            calendar.add(Calendar.MONTH, 6);
            currentSubscription.setUniqueCode("SAA"+'-'+tempId);
        }
        Date endDate = calendar.getTime();
        currentSubscription.setEndDate(endDate);

        return this;

    }

     public SubscriptionBuilder addBuyerUsername(String buyerUsername) {
         currentSubscription.setUsername(buyerUsername);
         return this;
     }

    public Subscription build() {
        return currentSubscription;
    }

    public SubscriptionBuilder setCurrent(Subscription Subscription) {
        currentSubscription = Subscription;
        return this;
    }
}
