package id.ac.ui.cs.advprog.subscriptionmanagement.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@Getter @Setter
public class SubscriptionBox {
    String id;
    String name;
    String type;
    int price;
    List<Item> items;
    // Rating rating;
}
