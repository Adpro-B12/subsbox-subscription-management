package id.ac.ui.cs.advprog.subscriptionmanagement.controller;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionmanagement.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/all")

    public ResponseEntity<Page<SubscriptionBox>> getAllSubscriptionBoxes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SubscriptionBox> boxesPage = subscriptionService.getAllBoxes(pageable);
        return new ResponseEntity<>(boxesPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionBox> getSubscriptionBox(@PathVariable Long id) {
        SubscriptionBox box = subscriptionService.findBoxById(id);
        return box != null ? new ResponseEntity<>(box, HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @GetMapping("/price")
    public ResponseEntity<Page<SubscriptionBox>> getFilteredSubscriptionBoxesByPrice(
            @RequestParam(required = false) int minPrice,

            @RequestParam(required = false) int maxPrice,
            Pageable pageable) {
                Page<SubscriptionBox> boxesPage = subscriptionService.getFilteredBoxesByPrice(minPrice, maxPrice, pageable);
                return new ResponseEntity<>(boxesPage, HttpStatus.OK);

    }

    @GetMapping("/name")
    public ResponseEntity<List<SubscriptionBox>> getFilteredSubscriptionBoxesByName(
            @RequestParam(required = false) String name) {
        List<SubscriptionBox> boxes = subscriptionService.getFilteredBoxesByName(name).stream()
                .sorted(Comparator.comparing(SubscriptionBox::getId))
                .collect(Collectors.toList());
        return new ResponseEntity<>(boxes, HttpStatus.OK);
    }

    @PostMapping("/subscribe/{id}")

    public ResponseEntity<Subscription> subscribe(@PathVariable Long id, @RequestBody Map<String, String> requestBody){
        try {

            String username = requestBody.get("username");
            String type = requestBody.get("type");

            Subscription subscription = subscriptionService.createSubscription(id, type, username);
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

    @GetMapping("/allsubs")
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions().stream()
                .sorted(Comparator.comparing(Subscription::getId))
                .collect(Collectors.toList());
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<List<Subscription>> getUserSubscriptions(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        List<Subscription> subscriptions = subscriptionService.getFilteredSubscriptionsByUsername(username).stream()
                .sorted(Comparator.comparing(Subscription::getId))
                .collect(Collectors.toList());
        return subscriptions.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(subscriptions);
    }

    @GetMapping("/subscriptions_by_status")
    public ResponseEntity<List<Subscription>> getSubscriptionByStatus(@RequestParam String status) {
        List<Subscription> subscriptions = subscriptionService.getFilteredSubscriptionsByStatus(status).stream()
                .sorted(Comparator.comparing(Subscription::getId))
                .collect(Collectors.toList());
        return subscriptions.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(subscriptions);
    }

    @PostMapping("/set_status/{subId}")
    public ResponseEntity<Subscription> setSubscriptionStatus(@PathVariable Long subId, @RequestBody Map<String, String> requestBody) {
        try {
            String status = requestBody.get("status");
            String role = requestBody.get("role");
            if(!role.equals("ADMIN")) {
                return ResponseEntity.badRequest().build();
            }
            Subscription subscription = subscriptionService.setSubscriptionStatus(subId, status);
            return new ResponseEntity<>(subscription, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
