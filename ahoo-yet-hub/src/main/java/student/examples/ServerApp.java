package student.examples;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import student.examples.comm.CommandType;
import student.examples.comm.ServerCommand;
import student.examples.config.Configuration;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp
{
    final static Logger logger = LoggerFactory.getLogger(ServerApp.class);

    public static void main(String[] args) throws IOException {
        logger.info("Started");
        ServerSocket serverSocket = new ServerSocket(Configuration.PORT);
        Socket clientSocket = serverSocket.accept();

        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
        ServerCommand turnOnCommand = new ServerCommand(CommandType.TURN_ON);

        oos.writeObject(turnOnCommand);

        logger.info("Stopped");
    }
}
