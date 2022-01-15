package fireround.rpc.example;

import java.math.BigDecimal;

public class OrderServiceImpl implements OrderService {

    @Override
    public Order createOrder(CreateOrderRequest createOrderRequest) {
        Order order = new Order();
        order.setId(System.currentTimeMillis());
        order.setRequestNo(createOrderRequest.getRequestNo());
        order.setAmount(new BigDecimal(100));
        order.setProdName("iPhone");
        return order;
    }
}
