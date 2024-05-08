package id.ac.ui.cs.advprog.subscriptionmanagement.service;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Builder.SubscriptionBuilder;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionRepository;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SubscriptionImpl implements SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private SubscriptionBoxRepository subscriptionBoxRepository;

    @Autowired
    private SubscriptionBuilder subscriptionBuilder;

    @Override
    public List<SubscriptionBox> getAllBoxes() {
        return subscriptionBoxRepository.findAll();
    }

    @Override
    public List<SubscriptionBox> getFilteredBoxesByPrice(int minPrice, int maxPrice) {
        return subscriptionBoxRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<SubscriptionBox> getFilteredBoxesByName(String name) {
        return subscriptionBoxRepository.findByNameContaining(name);
    }

    @Override
    public Subscription createSubscription(Long BoxId, String buyerUsername) {

        SubscriptionBox subscriptionBox = subscriptionBoxRepository.findById(BoxId).get();
        System.out.println("masuk create");
        subscriptionBuilder = new SubscriptionBuilder();
        Subscription newSubscription = subscriptionBuilder.reset()
                .addIdBox(BoxId)
                .addUniqueCode(subscriptionBox)
                .addBuyerUsername(buyerUsername)
                .build();
        return subscriptionRepository.save(newSubscription);
    }

    @Override
    public Subscription cancelSubscription(String uniqueCode) {
        Subscription subscription = subscriptionRepository.findByUniqueCode(uniqueCode);
        subscription.setStatus("CANCELLED");
        return subscription;
    }

}