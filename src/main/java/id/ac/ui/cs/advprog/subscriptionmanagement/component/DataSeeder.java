package id.ac.ui.cs.advprog.subscriptionmanagement.component;

import id.ac.ui.cs.advprog.subscriptionmanagement.model.Item;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionmanagement.model.Subscription;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionBoxRepository;
import id.ac.ui.cs.advprog.subscriptionmanagement.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class DataSeeder implements CommandLineRunner {
    @Autowired
    private SubscriptionBoxRepository subscriptionBoxRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public void run(String... args) throws Exception {
        // Real Madrid themed box
        SubscriptionBox box1 = new SubscriptionBox("Real Madrid Box", "MTH", 250, 1L);
        Item item1 = new Item("Real Madrid Home 2023/2024");
        Item item2 = new Item("Real Madrid Away 2023/2024");
        box1.setItems(new ArrayList<>(Arrays.asList(item1, item2)));
        subscriptionBoxRepository.save(box1);

        // Manchester United themed box
        SubscriptionBox box2 = new SubscriptionBox("Manchester United Box", "QTR", 500, 2L);
        Item item3 = new Item("Manchester United Home 2023/2024");
        Item item4 = new Item("Manchester United Away 2023/2024");
        box2.setItems(new ArrayList<>(Arrays.asList(item3, item4)));
        subscriptionBoxRepository.save(box2);

        // Barcelona themed box
        SubscriptionBox box3 = new SubscriptionBox("Barcelona Box", "SAA", 750, 3L);
        Item item5 = new Item("Barcelona Home 2023/2024");
        Item item6 = new Item("Barcelona Away 2023/2024");
        box3.setItems(new ArrayList<>(Arrays.asList(item5, item6)));
        subscriptionBoxRepository.save(box3);

        //
//        Subscription subscription1 = new Subscription("alifbintang1",  1L, 1L);
//        subscriptionRepository.save(subscription1);
//
//        //
//        Subscription subscription2 = new Subscription("alifbintang1",  1L, 2L);
//        subscriptionRepository.save(subscription2);
//
//        //
//        Subscription subscription3 = new Subscription("alifbintang1",  3L, 3L);
//        subscriptionRepository.save(subscription3);
//
//        //
//        Subscription subscription4 = new Subscription("akmalramadhan",  1L, 4L);
//        subscriptionRepository.save(subscription4);

    }
}