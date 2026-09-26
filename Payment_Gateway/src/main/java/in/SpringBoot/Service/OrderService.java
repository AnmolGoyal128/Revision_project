package in.SpringBoot.Service;

import in.SpringBoot.Payment.paymentService;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
    private final paymentService paymentService;
    public OrderService(paymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder() {

        paymentService.payment();

        System.out.println("Placing Order");

    }

}
