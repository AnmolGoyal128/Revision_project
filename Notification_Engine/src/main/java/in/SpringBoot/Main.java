package in.SpringBoot;

import in.SpringBoot.Notification.EmailNotification;
import in.SpringBoot.Notification.NotificationService;
import in.SpringBoot.Notification.SmsNotification;
import in.SpringBoot.Service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.ObjectInputFilter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        NotificationService notificationService = new SmsNotification();

        OrderService orderService = new OrderService(notificationService);
        orderService.setNotification(notificationService);
        orderService.placeOrder();

    }
}
