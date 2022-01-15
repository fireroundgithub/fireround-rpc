package fireround.rpc.example;

import fireround.rpc.core.RemoteServiceFactory;
import fireround.rpc.core.ServiceRegister;

public class OrderServiceConsumer {

    public static void main(String[] args) {
        // consumer get provider stub
        OrderService orderService = RemoteServiceFactory.createService(OrderService.class);
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setRequestNo(String.valueOf(System.currentTimeMillis()));
        // rpc invoke
        Order order = orderService.createOrder(createOrderRequest);
        System.out.println("Create order successfully!");
        System.out.println("Order amount is (CNY)" + order.getAmount());
    }
}
