package fireround.rpc.core;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class RemoteServiceFactory {

    @SuppressWarnings("unchecked")
    public static <T> T createService(Class<T> serviceClass) {
        if (serviceClass.isInterface()) {
            return (T) Proxy.newProxyInstance(
                    serviceClass.getClassLoader(), new Class<?>[]{serviceClass}, new RpcProxyStub());
        } else {
            throw new IllegalStateException("Service class must be interface");
        }
    }

    private static class RpcProxyStub implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            RpcRequest rpcRequest = new RpcRequest(method.getName(), proxy.getClass().getInterfaces()[0].getCanonicalName(), args);
            
            // auto detect
            Socket socket = new Socket("localhost", 8080);

            RpcResponse rpcResponse;
            try (ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
                output.writeObject(rpcRequest);
                output.flush();
                rpcResponse = (RpcResponse) input.readObject();
            }

            return rpcResponse.getResult();
        }
    }


}
