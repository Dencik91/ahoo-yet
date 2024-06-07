package student.examples.devices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import student.examples.config.Configuration;
import student.examples.devices.VacuumCleaner;
import student.examples.comm.CommandType;
import student.examples.comm.ServerCommand;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class VacuumCleanerApp
{
    final static Logger logger = LoggerFactory.getLogger(VacuumCleanerApp.class);
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        logger.info("Starting logs");
        VacuumCleaner vacuumCleaner = new VacuumCleaner();

        logger.info(String.format("%b",vacuumCleaner.isOn()));

        Socket socket = new Socket(Configuration.HOST,Configuration.PORT);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        ServerCommand serverCommand = (ServerCommand) in.readObject();

        if(serverCommand.getType().equals(CommandType.TURN_ON)){
            vacuumCleaner.switchOn();
        }
        logger.info(String.format("%b",vacuumCleaner.isOn()));

        logger.info("Connected to " + Configuration.HOST + ":" + Configuration.PORT);

        logger.info("Stopping logs");
    }
}
