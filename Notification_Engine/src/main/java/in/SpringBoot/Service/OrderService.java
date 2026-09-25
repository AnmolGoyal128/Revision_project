package in.SpringBoot.Service;

import org.springframework.stereotype.Service;


import in.SpringBoot.Notification.EmailNotification;
import in.SpringBoot.Notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
@Service
public class OrderService {


    // Dependency Injection of Notification Service into orderService
    @Autowired
    private NotificationService notificationService;

    public OrderService(
                        NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void placeOrder() {

        System.out.println("Order placed successfully.");

        notificationService.sendNotification(
                "Your order has been placed successfully."
        );

    }

    public void setNotification(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
}
