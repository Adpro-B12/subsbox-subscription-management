package id.ac.ui.cs.advprog.subscriptionmanagement.service;

import id.ac.ui.cs.advprog.subscriptionmanagement.handler.ResourceNotFoundException;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.Builder.SubscriptionBuilder;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionBoxRepository;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionBoxRepository subscriptionBoxRepository;
    private SubscriptionBuilder subscriptionBuilder;

    @Autowired
    public SubscriptionImpl(SubscriptionRepository subscriptionRepository, SubscriptionBoxRepository subscriptionBoxRepository, SubscriptionBuilder subscriptionBuilder) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionBoxRepository = subscriptionBoxRepository;
        this.subscriptionBuilder = subscriptionBuilder;
    }

    @Override
    public List<SubscriptionBox> getAllBoxes() { return subscriptionBoxRepository.findAll(); }

    @Override
    public List<Subscription> getAllSubscriptions() { return subscriptionRepository.findAll(); }

    @Override
    public List<SubscriptionBox> getFilteredBoxesByPrice(int minPrice, int maxPrice) {
        return subscriptionBoxRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<SubscriptionBox> getFilteredBoxesByName(String name) {
        return subscriptionBoxRepository.findByNameContaining(name);
    }

    @Override
    public SubscriptionBox findBoxById(Long id) throws ResourceNotFoundException {
        return subscriptionBoxRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subscription box not found with id: " + id));
    }

    @Override
    public Subscription findSubById(Long id) throws ResourceNotFoundException {
        return subscriptionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subscription not found with id: " + id));
    }

    @Override
    public Subscription createSubscription(Long boxId, String type, String buyerUsername) {
        subscriptionBuilder = new SubscriptionBuilder();
        Subscription newSubscription = subscriptionBuilder.reset()
                .addIdBox(boxId)
                .addUniqueCode(type)
                .addBuyerUsername(buyerUsername)
                .build();
        return subscriptionRepository.save(newSubscription);
    }

    @Override
    public Subscription cancelSubscription(Long subId) {
        Subscription subscription = subscriptionRepository.findById(subId).get();
        subscription.setStatus("CANCELLED");
        subscriptionRepository.save(subscription);
        return subscription;
    }


    @Override
    public List<Subscription> getFilteredSubscriptionsByUsername(String buyerUsername) {
        return subscriptionRepository.findByUsername(buyerUsername);
    }

    @Override
    public List<Subscription> getFilteredSubscriptionsByStatus(String status) {
        return subscriptionRepository.findByStatus(status);
    }

    @Override
    public Subscription setSubscriptionStatus(Long subId, String status) {

        Subscription subscription = subscriptionRepository.findById(subId).get();
        subscription.setStatus(status);
        subscriptionRepository.save(subscription);

        return subscription;
    }

}