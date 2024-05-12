package id.ac.ui.cs.advprog.subscriptionmanagement.service;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.UUID;

public interface SubscriptionService {
//    public Subscription create(Subscription Subscription);

    // Fitur 1
    CompletableFuture<List<SubscriptionBox>> getAllBoxesAsync();
    CompletableFuture<List<SubscriptionBox>> getFilteredBoxesByPriceAsync(int minPrice, int maxPrice);
    CompletableFuture<List<SubscriptionBox>> getFilteredBoxesByNameAsync(String name);

    // Fitur 2
    CompletableFuture<Subscription> createSubscriptionAsync(Long boxId, String buyerUsername);
    CompletableFuture<Subscription> cancelSubscriptionAsync(String uniqueCode);

    // Fitur 3
    CompletableFuture<List<Subscription>> getFilteredSubscriptionsByStatusAsync(String status);
    CompletableFuture<List<Subscription>> getFilteredSubscriptionsByUsernameAsync(String buyerUsername);

    // Fitur 4
    CompletableFuture<Subscription> approveSubscriptionAsync(String uniqueCode);
    CompletableFuture<Subscription> rejectSubscriptionAsync(String uniqueCode);
    CompletableFuture<Subscription> setSubscriptionPendingAsync(String uniqueCode);
}