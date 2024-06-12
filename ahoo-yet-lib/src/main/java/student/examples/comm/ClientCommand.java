package student.examples.comm;

import java.io.Serializable;

public class ClientCommand implements Command, Serializable {
    private static final long serialVersionUID = 1L;
    private CommandType type;
    private Object body;

    public ClientCommand(CommandType type, Object body) {
        this.type = type;
        this.body = body;
    }

    @Override
    public CommandType getType() {
        return type;
    }

    public Object getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "ClientCommand{" +
                "type=" + type +
                ", body=" + body +
                '}';
    }
}
