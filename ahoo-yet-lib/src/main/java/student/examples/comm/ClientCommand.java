package student.examples.comm;

import java.io.Serializable;

public class ClientCommand implements Command, Serializable {
    private static final long serialVersionUID = 1L;
    private CommandType type;

    public ClientCommand(CommandType type) {
        this.type = type;
    }

    @Override
    public CommandType getType() {
        return type;
    }
}
