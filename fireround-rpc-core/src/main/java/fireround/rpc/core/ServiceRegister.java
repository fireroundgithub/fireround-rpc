package fireround.rpc.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceRegister {

    private final ExecutorService executorService = Executors.newFixedThreadPool(10, Thread::new);
    private final Map<String, Object> registeredClass = new ConcurrentHashMap<>();

    public ServiceRegister() throws IOException {
        init();
    }

    public <T> void register(String serviceName, T obj) {
        System.out.println("Register service: " + obj.getClass().getCanonicalName());
        registeredClass.put(serviceName, obj);
    }

    private void init() throws IOException {
        // start server Skeleton
        ServerSocket serverSocket = new ServerSocket(8080);
        new Thread(() -> {
            for (; ; ) {
                try {
                    Socket socket = serverSocket.accept();
                    executorService.submit(() -> handleSocket(socket));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void handleSocket(Socket socket) {
        try {
            // read IO stream
            // parse rpc package, use appointed serialize protocol
            try (ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
                RpcRequest rpcRequest = (RpcRequest) input.readObject();
                Class<?> clazz = Class.forName(rpcRequest.getClassName());
                Method method = clazz.getMethod(rpcRequest.getMethodName(), rpcRequest.getArgs()[0].getClass());
                Object result = method.invoke(registeredClass.get(rpcRequest.getClassName()), rpcRequest.getArgs());
                RpcResponse rpcResponse = new RpcResponse(result);
                out.writeObject(rpcResponse);
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }


        } catch (IOException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
