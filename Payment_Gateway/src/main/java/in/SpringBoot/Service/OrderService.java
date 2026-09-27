package in.SpringBoot.Service;

import in.SpringBoot.Payment.paymentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
    private final paymentService paymentService;
    public OrderService(@Qualifier("paytm") paymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder() {

        paymentService.payment();

        System.out.println("Placing Order");

    }

}
