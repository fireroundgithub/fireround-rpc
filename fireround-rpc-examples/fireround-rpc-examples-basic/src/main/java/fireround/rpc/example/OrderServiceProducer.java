package fireround.rpc.example;

import fireround.rpc.core.ServiceRegister;

import java.io.IOException;

public class OrderServiceProducer {

    public static void main(String[] args) throws IOException {
        // register provider and start thread for Skeleton service
        ServiceRegister serviceRegister = new ServiceRegister();
        serviceRegister.register(OrderService.class.getCanonicalName(), new OrderServiceImpl());
    }
}
