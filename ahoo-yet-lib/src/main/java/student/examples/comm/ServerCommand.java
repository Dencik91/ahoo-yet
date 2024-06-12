package student.examples.comm;

import java.io.Serializable;

public class ServerCommand implements Command, Serializable {
    private static final long serialVersionUID = 1L;
    private CommandType type;
    private Object body;

    public ServerCommand(CommandType type, Object body) {
        this.type = type;
        this.body = body;
    }

    @Override
    public CommandType getType() {
        return type;
    }

    @Override
    public Object getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "ServerCommand{" +
                "type=" + type +
                ", body=" + body +
                '}';
    }
}
