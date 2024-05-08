package id.ac.ui.cs.advprog.subscriptionmanagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Builder.SubscriptionBuilder;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;
import id.ac.ui.cs.advprog.subscriptionmanagement.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Subscription findByUniqueCode(String uniqueCode);
}
