package fireround.rpc.registry;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServiceRegistry {

    // serviceName -> registry info
    private Map<String, RegistryInfo> registry = new HashMap<String, RegistryInfo>();

    public RegistryInfo getRegistry(String serviceName) {
        return registry.get(serviceName); 
    }

    // start to receive register info from provider, and provide info for consumer
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1617);
        while (true) {
            Socket socket = serverSocket.accept();
        }
    }
}
