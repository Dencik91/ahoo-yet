package student.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import student.examples.comm.ClientCommand;
import student.examples.config.Configuration;
import student.examples.comm.CommandType;
import student.examples.comm.ServerCommand;
import student.examples.devices.VacuumCleaner;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class VacuumCleanerApp
{
    final static Logger logger = LoggerFactory.getLogger(VacuumCleanerApp.class);
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        logger.info("Starting logs");
        VacuumCleaner vacuumCleaner = new VacuumCleaner(1, "Atom");
        System.out.println(vacuumCleaner);

        logger.info(String.format("%b",vacuumCleaner.isOn()));

        Socket socket = new Socket(Configuration.HOST,Configuration.PORT);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        ServerCommand serverCommand = (ServerCommand) in.readObject();
        ClientCommand clientCommand = new ClientCommand(CommandType.ACKNOWLEDGE);

        if(serverCommand.getType().equals(CommandType.TURN_ON)){
            vacuumCleaner.switchOn();
            oos.writeObject(clientCommand);
        }

        logger.info(String.format("%b",vacuumCleaner.isOn()));

        serverCommand = (ServerCommand) in.readObject();
        if(serverCommand.getType().equals(CommandType.TURN_OFF)){
            vacuumCleaner.switchOff();
            oos.writeObject(clientCommand);
        }

        logger.info(String.format("%b",vacuumCleaner.isOn()));

        logger.info("Connected to " + Configuration.HOST + ":" + Configuration.PORT);

        logger.info("Stopping logs");
    }
}
