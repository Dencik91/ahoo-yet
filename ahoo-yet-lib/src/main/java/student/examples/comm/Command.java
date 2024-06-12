package student.examples.comm;

public interface Command {
    public CommandType getType();
    public Object getBody();
}
