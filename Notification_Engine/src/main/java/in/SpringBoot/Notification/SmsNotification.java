package in.SpringBoot.Notification;

import org.springframework.stereotype.Service;

@Service
public class SmsNotification implements NotificationService {


    @Override
    public void sendNotification(String s) {
        System.out.println("Notification sent via SMS ");
    }
}
