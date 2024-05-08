package id.ac.ui.cs.advprog.subscriptionmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uniqueCode;
    private String status;
    private Date startDate;
    private Date endDate;
    private Long subscriptionBoxId;

//    private User user;
}