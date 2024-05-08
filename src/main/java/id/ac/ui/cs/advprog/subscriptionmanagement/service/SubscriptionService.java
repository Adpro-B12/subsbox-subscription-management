package id.ac.ui.cs.advprog.subscriptionmanagement.service;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionRepository;

import java.util.List;
import java.util.UUID;

public interface SubscriptionService {
//    public Subscription create(Subscription Subscription);

    // Fitur 1
    public List<SubscriptionBox> getAllBoxes();
    public List<SubscriptionBox> getFilteredBoxesByPrice(int minPrice, int maxPrice);

    // Fitur 2


    // public List<Subscription> findAllByBuyerUsername(String buyerUsername);
}