package id.ac.ui.cs.advprog.subscriptionmanagement.service;

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
    private SubscriptionRepository SubscriptionRepository;

    @Autowired
    private SubscriptionBoxRepository SubscriptionBoxRepository;

    @Override
    public Subscription create(Subscription Subscription) {
        return SubscriptionRepository.save(Subscription);
    }

    @Override
    public List<Subscription> findAll() {
        return SubscriptionRepository.findAll();
    }

    @Override
    public Subscription findById(UUID SubscriptionId) {
        return SubscriptionRepository.findById(SubscriptionId);
    }

    @Override
    public Subscription update(Subscription Subscription) {
        return SubscriptionRepository.save(Subscription);
    }
    
    @Override
    public Subscription deleteSubscriptionById(UUID SubscriptionId) {
        return SubscriptionRepository.deleteById(SubscriptionId);
    }

    @Override
    public List<SubscriptionBox> getAllBoxes() {
        return SubscriptionBoxRepository.viewAll();
    }

    @Override
    public List<SubscriptionBox> getFilteredBoxesByPrice(int price) {
        return SubscriptionBoxRepository.filterByPrice(price);
    }
    // @Override
    // public List<Subscription> findAllByBuyerUsername(String buyerUsername) {
    //     return SubscriptionRepository.findAllByBuyerUsername(buyerUsername);
    // }
}