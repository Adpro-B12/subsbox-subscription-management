package id.ac.ui.cs.advprog.subscriptionmanagement.service;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Builder.SubscriptionBuilder;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionRepository;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
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
    public CompletableFuture<List<SubscriptionBox>> getAllBoxesAsync() {
        return CompletableFuture.supplyAsync(() -> subscriptionBoxRepository.findAll());
    }

    @Override
    public CompletableFuture<List<SubscriptionBox>> getFilteredBoxesByPriceAsync(int minPrice, int maxPrice) {
        return CompletableFuture.supplyAsync(() -> subscriptionBoxRepository.findByPriceBetween(minPrice, maxPrice));
    }

    @Override
    public CompletableFuture<List<SubscriptionBox>> getFilteredBoxesByNameAsync(String name) {
        return CompletableFuture.supplyAsync(() -> subscriptionBoxRepository.findByNameContaining(name));
    }

    @Override
    public CompletableFuture<Subscription> createSubscriptionAsync(Long boxId, String buyerUsername) {
        return CompletableFuture.supplyAsync(() -> {
            SubscriptionBox subscriptionBox = subscriptionBoxRepository.findById(boxId).orElse(null);
            if (subscriptionBox != null) {
                subscriptionBuilder = new SubscriptionBuilder();
                Subscription newSubscription = subscriptionBuilder.reset()
                        .addIdBox(boxId)
                        .addUniqueCode(subscriptionBox)
                        .addBuyerUsername(buyerUsername)
                        .build();
                return subscriptionRepository.save(newSubscription);
            } else {
                // Handle case where SubscriptionBox is not found
                return null;
            }
        });
    }

    @Override
    public CompletableFuture<Subscription> cancelSubscriptionAsync(String uniqueCode) {
        return CompletableFuture.supplyAsync(() -> {
            Subscription subscription = subscriptionRepository.findByUniqueCode(uniqueCode);
            if (subscription != null) {
                subscription.setStatus("CANCELLED");
                return subscriptionRepository.save(subscription);
            } else {
                // Handle case where Subscription is not found
                return null;
            }
        });
    }

    @Override
    public CompletableFuture<List<Subscription>> getFilteredSubscriptionsByUsernameAsync(String buyerUsername) {
        return CompletableFuture.supplyAsync(() -> subscriptionRepository.findByUsername(buyerUsername));
    }

    @Override
    public CompletableFuture<List<Subscription>> getFilteredSubscriptionsByStatusAsync(String status) {
        return CompletableFuture.supplyAsync(() -> subscriptionRepository.findByStatus(status));
    }

    @Override
    public CompletableFuture<Subscription> approveSubscriptionAsync(String uniqueCode) {
        return CompletableFuture.supplyAsync(() -> {
            Subscription subscription = subscriptionRepository.findByUniqueCode(uniqueCode);
            if (subscription != null) {
                subscription.setStatus("Approved");
                subscriptionRepository.save(subscription);
            }
            return subscription;
        });
    }

    @Override
    public CompletableFuture<Subscription> rejectSubscriptionAsync(String uniqueCode) {
        return CompletableFuture.supplyAsync(() -> {
            Subscription subscription = subscriptionRepository.findByUniqueCode(uniqueCode);
            if (subscription != null) {
                subscription.setStatus("Rejected");
                subscriptionRepository.save(subscription);
            }
            return subscription;
        });
    }

    @Override
    public CompletableFuture<Subscription> setSubscriptionPendingAsync(String uniqueCode) {
        return CompletableFuture.supplyAsync(() -> {
            Subscription subscription = subscriptionRepository.findByUniqueCode(uniqueCode);
            if (subscription != null) {
                subscription.setStatus("Pending");
                subscriptionRepository.save(subscription);
            }
            return subscription;
        });
    }
}