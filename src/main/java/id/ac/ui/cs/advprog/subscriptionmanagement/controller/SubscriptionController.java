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
import java.util.UUID;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/all")
    public ResponseEntity<List<SubscriptionBox>> getAllSubscriptionBoxes() {
        List<SubscriptionBox> boxes = subscriptionService.getAllBoxes();
        return new ResponseEntity<>(boxes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionBox> getSubscriptionBox(@PathVariable Long id) {
        SubscriptionBox box = subscriptionService.findBoxById(id);
        return box != null ? new ResponseEntity<>(box, HttpStatus.OK) : ResponseEntity.notFound().build();
    }
    @GetMapping("/price")
    public ResponseEntity<List<SubscriptionBox>> getFilteredSubscriptionBoxesByPrice(
            @RequestParam(required = false) int minPrice,
            @RequestParam(required = false) int maxPrice) {
        List<SubscriptionBox> boxes = subscriptionService.getFilteredBoxesByPrice(minPrice, maxPrice);
        return new ResponseEntity<>(boxes, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<SubscriptionBox>> getFilteredSubscriptionBoxesByName(
            @RequestParam(required = false) String name) {
        List<SubscriptionBox> boxes = subscriptionService.getFilteredBoxesByName(name);
        return new ResponseEntity<>(boxes, HttpStatus.OK);
    }

    @PostMapping("/subscribe/{id}")
    public ResponseEntity<Subscription> subscribe(@PathVariable Long id, @RequestBody Map<String, String> requestBody){
//
        try {

            String username = requestBody.get("username");
            Subscription subscription = subscriptionService.createSubscription(id, username);
            return new ResponseEntity<>(subscription, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }



    @PostMapping("/cancel/{subId}")
    public ResponseEntity<Subscription> cancelSubscription(@PathVariable Long subId) {
        try {
            Subscription subscription = subscriptionService.cancelSubscription(subId);
            return new ResponseEntity<>(subscription, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/subscriptions")
    public ResponseEntity<List<Subscription>> getUserSubscriptions(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        List<Subscription> subscriptions = subscriptionService.getFilteredSubscriptionsByUsername(username);
        return subscriptions.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(subscriptions);
    }

    @GetMapping("/subscriptions_by_status")
    public ResponseEntity<List<Subscription>> getSubscriptionByStatus(@RequestParam String status) {
        List<Subscription> subscriptions = subscriptionService.getFilteredSubscriptionsByStatus(status);
        return subscriptions.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(subscriptions);
    }

//    @PostMapping("/set_status")

}
