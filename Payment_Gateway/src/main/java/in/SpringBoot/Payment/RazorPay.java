package in.SpringBoot.Payment;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
@Primary
@Component("razorPay")
public class RazorPay implements paymentService {
    public void payment() {
        System.out.println("payment Done via RazorPay");
    }
}
