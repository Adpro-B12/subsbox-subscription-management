package id.ac.ui.cs.advprog.subscriptionmanagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Subscription findByUniqueCode(String uniqueCode);

    List<Subscription> findByUsername(String buyerUsername);

    List<Subscription> findByStatus(String status);
}
