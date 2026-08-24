package in.SpringBoot;

import in.SpringBoot.Service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.ObjectInputFilter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        ApplicationContext context = new AnnotationConfigApplicationContext(ObjectInputFilter.Config.class);

        OrderService orderService =
                context.getBean(OrderService.class);

        orderService.placeOrder();
        System.out.println("Hello World");

    }
}
