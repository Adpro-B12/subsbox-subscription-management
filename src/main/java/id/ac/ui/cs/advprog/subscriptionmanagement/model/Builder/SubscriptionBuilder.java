package id.ac.ui.cs.advprog.subscriptionmanagement.model.Builder;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Enum.SubscriptionStatus;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Calendar;
import java.util.Date;

import java.util.UUID;

public class SubscriptionBuilder {
    private Subscription currentSubscription;

    private SubscriptionBoxRepository SubscriptionBoxRepository;

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

    public SubscriptionBuilder addUniqueCode() {
        Date startDate = new Date();
        currentSubscription.setStartDate(startDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        Long idSubscriptionBox = currentSubscription.getSubscriptionBoxId();
        SubscriptionBox subscriptionBox = SubscriptionBoxRepository.findById(idSubscriptionBox).orElse(null);

        if(subscriptionBox == null) {
            return null;
        }

        String subscriptionBoxType = subscriptionBox.getType();

        if (subscriptionBoxType.equals("MONTHLY")) {
            calendar.add(Calendar.MONTH, 1);
            currentSubscription.setUniqueCode("MTH"+'-'+currentSubscription.getId().toString());
        } else if (subscriptionBoxType.equals("QUARTERLY")) {
            calendar.add(Calendar.MONTH, 3);
            currentSubscription.setUniqueCode("QTR"+'-'+currentSubscription.getId().toString());
        } else if(subscriptionBoxType.equals("SEMIANNUAL")) {
            calendar.add(Calendar.MONTH, 6);
            currentSubscription.setUniqueCode("SAA"+'-'+currentSubscription.getId().toString());
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
