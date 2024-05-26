package id.ac.ui.cs.advprog.subscriptionmanagement.service;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SubscriptionService {

    // Fitur 1
    public Page<SubscriptionBox> getAllBoxes(Pageable pageable);
    public Page<SubscriptionBox> getFilteredBoxesByPrice(int minPrice, int maxPrice, Pageable pageable);
    public List<SubscriptionBox> getFilteredBoxesByName(String name);
    public SubscriptionBox findBoxById(Long id);
    public Subscription findSubById(Long id);

    // Fitur 2
    public Subscription createSubscription(Long boxId, String type, String buyerUsername);
    public Subscription cancelSubscription(Long subId);

    // Fitur 3
    public List<Subscription> getFilteredSubscriptionsByStatus(String status);
    public List<Subscription> getFilteredSubscriptionsByUsername(String buyerUsername);

    // Fitur 4
    public List<Subscription> getAllSubscriptions();
    public Subscription setSubscriptionStatus(Long subId, String status);

}