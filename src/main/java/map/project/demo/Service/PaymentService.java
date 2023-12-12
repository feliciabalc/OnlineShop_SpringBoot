package map.project.demo.Service;

import map.project.demo.Entities.Orders;
import map.project.demo.Entities.PaymentStrategy;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public void processPayment(Orders order, PaymentStrategy paymentStrategy) {
        paymentStrategy.processPayment(order);
    }
}
