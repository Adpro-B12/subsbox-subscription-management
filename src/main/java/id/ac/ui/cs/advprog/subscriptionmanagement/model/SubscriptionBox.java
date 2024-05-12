package id.ac.ui.cs.advprog.subscriptionmanagement.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "subscription_boxes")
public class SubscriptionBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    String type;
    int price;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Item> items;
    // Rating rating;
    public SubscriptionBox() {
    }

    public SubscriptionBox(String name,  String type, int price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }
}
