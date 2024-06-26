package student.examples;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import student.examples.comm.ClientCommand;
import student.examples.comm.Command;
import student.examples.comm.CommandType;
import student.examples.comm.ServerCommand;
import student.examples.config.Configuration;
import student.examples.devices.DeviceInterface;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServerApp
{
    final static Logger logger = LoggerFactory.getLogger(ServerApp.class);
    private Set<Map<String, Object>> connections;
    private ServerSocket serverSocket;

    public ServerApp (int port) throws IOException {
        create(port);
    }

    private void create(int port) throws IOException {
        connections = new HashSet<>();
        serverSocket = new ServerSocket(port, 0, InetAddress.getLocalHost());
    }

    private void listen() throws IOException, ClassNotFoundException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos;
            Command command = (Command) ois.readObject();
            if (command.getType() == CommandType.IDENTITY) {
                logger.info(String.format("SERVER: Client connected: %s", clientSocket.getInetAddress()));
                DeviceInterface device = (DeviceInterface) command.getBody();
                Map<String,Object> client = new HashMap<>();
                client.put("socket", clientSocket);
                client.put("device", device);
                connections.add(client);
                logger.info(String.format("SERVER: Send Acknowledge to client"));
                ServerCommand serverCommand = new ServerCommand(CommandType.ACKNOWLEDGE, null);
                oos = new ObjectOutputStream(clientSocket.getOutputStream());
                oos.writeObject(serverCommand);
            }
            connections.forEach(System.out::println);



        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        logger.info("SERVER: Initializing");
        ServerApp app = new ServerApp(Configuration.PORT);
        logger.info("SERVER: Starting");
        app.listen();
        logger.info("SERVER: Shut down");
    }
}
