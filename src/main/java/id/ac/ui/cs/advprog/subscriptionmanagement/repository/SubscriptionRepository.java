package id.ac.ui.cs.advprog.subscriptionmanagement.repository;


import id.ac.ui.cs.advprog.subscriptionmanagement.model.Builder.SubscriptionBuilder;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;
import id.ac.ui.cs.advprog.subscriptionmanagement.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class SubscriptionRepository {
    @Autowired
    private SubscriptionBuilder SubscriptionBuilder;
    private List<Subscription> SubscriptionList = new ArrayList();

    public Subscription save(Subscription Subscription) {

        for (int index = 0; index < SubscriptionList.size(); index++) {
            Subscription currentSubscription = SubscriptionList.get(index);
            if (currentSubscription.getId().equals(Subscription.getId())) {
                Subscription newSubscription = SubscriptionBuilder.reset()
                        .setCurrent(Subscription)
                        .addStatus(Subscription.getStatus()).build();

                SubscriptionList.set(index, newSubscription);
                return newSubscription;
            }
        }

        Subscription newSubscription = SubscriptionBuilder.reset()
                .addStatus(Subscription.getStatus()).build();
        SubscriptionList.add(newSubscription);
        return newSubscription;
    }

    public Subscription findById(UUID id) {
        for (int index = 0; index < SubscriptionList.size(); index++) {
            Subscription currentSubscription = SubscriptionList.get(index);
            if (currentSubscription.getId().equals(id)) {
                return currentSubscription;
            }
        }
        return null;
    }

    public List<Subscription> findAll() {
        return SubscriptionList;
    }


    public Subscription deleteById(UUID id) {
        for (int index = 0; index < SubscriptionList.size(); index++) {
            Subscription currentSubscription = SubscriptionList.get(index);
            if (currentSubscription != null && currentSubscription.getId().equals(id)) {
                SubscriptionList.remove(index);
                return currentSubscription;
            }
        }
        return null;
    }
    // public List<Subscription> findAllByBuyerUsername(String buyerUsername) {
    //     List<Subscription> tempSubscriptionList = new ArrayList<>();
    //     for (Subscription Subscription : SubscriptionList) {
    //         if (Subscription != null && Subscription.getBuyerUsername().equals(buyerUsername)) {
    //             tempSubscriptionList.add(Subscription);
    //         }
    //     }
    //     return tempSubscriptionList;
    // }
}
