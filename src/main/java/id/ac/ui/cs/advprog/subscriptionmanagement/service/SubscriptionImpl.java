package id.ac.ui.cs.advprog.subscriptionmanagement.service;

import id.ac.ui.cs.advprog.subscriptionmanagement.handler.ResourceNotFoundException;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.Builder.SubscriptionBuilder;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionBoxRepository;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<SubscriptionBox> getFilteredBoxesByPrice(int minPrice, int maxPrice, Pageable pageable) {
        Page<SubscriptionBox> page = subscriptionBoxRepository.findByPriceBetween(minPrice, maxPrice, pageable);
        return page.getContent();
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
    public Subscription createSubscription(Long BoxId, String buyerUsername) {

        SubscriptionBox subscriptionBox = subscriptionBoxRepository.findById(BoxId).get();
        subscriptionBuilder = new SubscriptionBuilder();
        Subscription newSubscription = subscriptionBuilder.reset()
                .addIdBox(BoxId)
                .addUniqueCode(subscriptionBox)
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
    public Subscription setSubscriptionStatus(String uniqueCode, String status) {

        Subscription subscription = subscriptionRepository.findByUniqueCode(uniqueCode);
        if (subscription != null) {
            subscription.setStatus(status);
            subscriptionRepository.save(subscription);
        }
        return subscription;
    }

//    @Override
//    public Subscription approveSubscription(String uniqueCode) {
//        Subscription subscription = subscriptionRepository.findByUniqueCode(uniqueCode);
//        if (subscription != null) {
//            subscription.setStatus("Approved");
//            subscriptionRepository.save(subscription);
//        }
//        return subscription;
//    }
//
//    @Override
//    public Subscription rejectSubscription(String uniqueCode) {
//        Subscription subscription = subscriptionRepository.findByUniqueCode(uniqueCode);
//        if (subscription != null) {
//            subscription.setStatus("Rejected");
//            subscriptionRepository.save(subscription);
//        }
//        return subscription;
//    }
//
//    @Override
//    public Subscription setSubscriptionPending(String uniqueCode) {
//        Subscription subscription = subscriptionRepository.findByUniqueCode(uniqueCode);
//        if (subscription != null) {
//            subscription.setStatus("Pending");
//            subscriptionRepository.save(subscription);
//        }
//        return subscription;
//    }

}