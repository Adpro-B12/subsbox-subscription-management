package id.ac.ui.cs.advprog.subscriptionmanagement.controller;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionmanagement.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<List<SubscriptionBox>> getAllSubscriptionBoxes() {
        List<SubscriptionBox> boxes = subscriptionService.getAllBoxes();
        return new ResponseEntity<>(boxes, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionBox>> getFilteredSubscriptionBoxesByPrice(
            @RequestParam(required = false) int minPrice,
            @RequestParam(required = false) int maxPrice) {
        List<SubscriptionBox> boxes = subscriptionService.getFilteredBoxesByPrice(minPrice, maxPrice);
        return new ResponseEntity<>(boxes, HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable UUID id) {
//        Subscription subscription = subscriptionService.findById(id);
//        if (subscription != null) {
//            return ResponseEntity.ok(subscription);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) {
//        Subscription createdSubscription = subscriptionService.create(subscription);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubscription);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Subscription> updateSubscription(@PathVariable Long id, @RequestBody Subscription subscription) {
//        subscription.setId(id);
//        Subscription updatedSubscription = subscriptionService.update(subscription);
//        return ResponseEntity.ok(updatedSubscription);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteSubscription(@PathVariable UUID id) {
//        subscriptionService.deleteSubscriptionById(id);
//        return ResponseEntity.noContent().build();
//    }
}
