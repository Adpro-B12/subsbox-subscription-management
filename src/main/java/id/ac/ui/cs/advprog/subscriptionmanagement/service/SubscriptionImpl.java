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
    private SubscriptionRepository SubscriptionRepository;
    @Autowired
    private SubscriptionBoxRepository SubscriptionBoxRepository;
    @Autowired
    private SubscriptionBuilder SubscriptionBuilder;

    @Override
    public List<SubscriptionBox> getAllBoxes() {
        return SubscriptionBoxRepository.findAll();
    }

    @Override
    public List<SubscriptionBox> getFilteredBoxesByPrice(int minPrice, int maxPrice) {
        return SubscriptionBoxRepository.findByPriceRange(minPrice, maxPrice);
    }

}