package id.ac.ui.cs.advprog.subscriptionmanagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Repository
public interface SubscriptionBoxRepository extends JpaRepository<SubscriptionBox, Long>{
    List<SubscriptionBox> findByPriceRange(int minPrice, int maxPrice);
}