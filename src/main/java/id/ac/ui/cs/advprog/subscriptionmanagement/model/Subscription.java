package id.ac.ui.cs.advprog.subscriptionmanagement.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter @Setter
public class Subscription {
    private UUID id;
    private String uniqueCode;
    private String status;
    private Date startDate;
    private Date endDate;
    private SubscriptionBox subscriptionBox;

//    private User user;
}