package id.ac.ui.cs.advprog.subscriptionmanagement.service;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;

import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionRepository;

import java.util.List;
import java.util.UUID;

public interface SubscriptionService {
    public Subscription create(Subscription Subscription);
    public List<Subscription> findAll();
    public Subscription findById(UUID SubscriptionId);
    public Subscription update(Subscription Subscription);
    public Subscription deleteSubscriptionById(UUID SubscriptionId);
    // public List<Subscription> findAllByBuyerUsername(String buyerUsername);
}