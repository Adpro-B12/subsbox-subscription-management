package id.ac.ui.cs.advprog.subscriptionmanagement.service;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionRepository;

import java.util.List;
import java.util.UUID;

public interface SubscriptionService {

    // Fitur 1
    public List<SubscriptionBox> getAllBoxes();
    public List<SubscriptionBox> getFilteredBoxesByPrice(int minPrice, int maxPrice);
    public List<SubscriptionBox> getFilteredBoxesByName(String name);
    public SubscriptionBox findBoxById(Long id);
    public Subscription findSubById(Long id);

    // Fitur 2
    public Subscription createSubscription(Long BoxId, String buyerUsername);
    public Subscription cancelSubscription(Long subId);

    // Fitur 3
    public List<Subscription> getFilteredSubscriptionsByStatus(String status);
    public List<Subscription> getFilteredSubscriptionsByUsername(String buyerUsername);

    // Fitur 4
    public List<Subscription> getAllSubscriptions();
    public Subscription setSubscriptionStatus(Long subId, String status);

}