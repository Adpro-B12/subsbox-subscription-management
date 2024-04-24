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
    private User user;
    private SubscriptionBox subscriptionBox;

    private Subscription(Builder builder) {
        this.uniqueCode = builder.uniqueCode;
        this.status = builder.status;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.user = builder.user;
        this.subscriptionBox = builder.subscriptionBox;
    }

    public static class Builder {
        private String uniqueCode;
        private String status;
        private Date startDate;
        private Date endDate;
        private User user;
        private SubscriptionBox subscriptionBox;

        public Builder(String status, User user, SubscriptionBox subscriptionBox) {
            this.status = status;
            this.user = user;
            this.subscriptionBox = subscriptionBox;
        }

        public Builder uniqueCode(String uniqueCode) {
            this.uniqueCode = uniqueCode;
            return this;
        }

        public Builder startDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Subscription build() {
            return new Subscription(this);
        }
    }
}
