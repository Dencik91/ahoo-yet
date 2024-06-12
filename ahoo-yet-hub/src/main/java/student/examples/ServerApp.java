package student.examples;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import student.examples.comm.ClientCommand;
import student.examples.comm.CommandType;
import student.examples.comm.ServerCommand;
import student.examples.config.Configuration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServerApp
{
    final static Logger logger = LoggerFactory.getLogger(ServerApp.class);
    private Set<Map<String, Object>> connections;

    public ServerApp () {
        connections = new HashSet<>();
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        logger.info("Started");
        ServerSocket serverSocket = new ServerSocket(Configuration.PORT);
        Socket clientSocket = serverSocket.accept();

        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
        ServerCommand turnOnCommand = new ServerCommand(CommandType.TURN_ON);
        oos.writeObject(turnOnCommand);

        ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
        ClientCommand clientCommand = (ClientCommand) ois.readObject();

        logger.info(String.format("%s",clientCommand.getType()));

        ServerCommand turnOffCommand = new ServerCommand(CommandType.TURN_OFF);
        if(clientCommand.getType().equals(CommandType.ACKNOWLEDGE)) {
            oos.writeObject(turnOffCommand);
        }

        clientCommand = (ClientCommand) ois.readObject();

        logger.info(String.format("%s",clientCommand.getType()));

        logger.info("Stopped");
    }
}
