package student.examples;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServerApp
{
    public static void main(String[] args) {
        Set<Map<String, Object>> connections;
        connections = new HashSet<>();

        Map<String, Object> client1 = new HashMap<>();
        Map<String, Object> client2 = new HashMap<>();
        client1.put("ip", "192.168.0.1");
        client1.put("id", 1); //auto boxing
        client2.put("ip", "192.168.0.2");
        client2.put("id", 2); //auto boxing

        connections.add(client1);
        connections.add(client2);

        connections.forEach(client-> System.out.println(client));
    }

    public static void dummyMethod() {
        System.out.println("Executing dummy method");
    }
}
