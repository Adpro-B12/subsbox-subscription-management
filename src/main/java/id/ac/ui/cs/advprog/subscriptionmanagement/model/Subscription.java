package id.ac.ui.cs.advprog.subscriptionmanagement.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Subscription {
    private Long id;
    private String uniqueCode;
    private String status;
    private Date startDate;
    private Date endDate;
//    private User user;
//    private SubscriptionBox subscriptionBox;
}
