package in.SpringBoot.Payment;

import org.springframework.stereotype.Component;

@Component
public class RazorPay implements paymentService {
    public void payment() {
        System.out.println("payment Done via RazorPay");
    }
}
