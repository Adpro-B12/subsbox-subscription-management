package id.ac.ui.cs.advprog.subscriptionmanagement.controller;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionRepository;
import id.ac.ui.cs.advprog.subscriptionmanagement.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.UUID;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/all")
    public CompletableFuture<ResponseEntity<List<SubscriptionBox>>> getAllSubscriptionBoxes() {
        return subscriptionService.getAllBoxesAsync()
                .thenApply(boxes -> ResponseEntity.ok(boxes))
                .exceptionally(e -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/price")
    public CompletableFuture<ResponseEntity<List<SubscriptionBox>>> getFilteredSubscriptionBoxesByPrice(
            @RequestParam(required = false) int minPrice,
            @RequestParam(required = false) int maxPrice) {
        return subscriptionService.getFilteredBoxesByPriceAsync(minPrice, maxPrice)
                .thenApply(boxes -> ResponseEntity.ok(boxes))
                .exceptionally(e -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/name")
    public CompletableFuture<ResponseEntity<List<SubscriptionBox>>> getFilteredSubscriptionBoxesByName(
            @RequestParam(required = false) String name) {
        return subscriptionService.getFilteredBoxesByNameAsync(name)
                .thenApply(boxes -> ResponseEntity.ok(boxes))
                .exceptionally(e -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PostMapping("/subscribe/{id}")
    public CompletableFuture<ResponseEntity<Subscription>> subscribe(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        return subscriptionService.createSubscriptionAsync(id, username)
                .thenApply(subscription -> ResponseEntity.ok(subscription))
                .exceptionally(e -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/cancel")
    public CompletableFuture<ResponseEntity<Subscription>> cancelSubscription(@RequestBody Map<String, String> requestBody) {
        String uniqueCode = requestBody.get("uniqueCode");
        return subscriptionService.cancelSubscriptionAsync(uniqueCode)
                .thenApply(subscription -> ResponseEntity.ok(subscription))
                .exceptionally(e -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/subscriptions")
    public CompletableFuture<ResponseEntity<List<Subscription>>> getUserSubscriptions(@RequestParam String ownerUsername) {
        return subscriptionService.getFilteredSubscriptionsByUsernameAsync(ownerUsername)
                .thenApply(subscriptions -> ResponseEntity.ok(subscriptions))
                .exceptionally(e -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/subscriptions_by_status")
    public CompletableFuture<ResponseEntity<List<Subscription>>> getSubscriptionByStatus(@RequestParam String status) {
        return subscriptionService.getFilteredSubscriptionsByStatusAsync(status)
                .thenApply(subscriptions -> ResponseEntity.ok(subscriptions))
                .exceptionally(e -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
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
