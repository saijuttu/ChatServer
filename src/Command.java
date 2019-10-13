import java.io.Serializable;
import java.util.ArrayList;
public class Command implements Serializable
{
    private Integer command                 = 0;
    private Object commandData          = 0;
    public static final int JOIN 	    = 1;
    public static final int SENT 	    = 2;
    public static final int LOGOFF	    = 3;
    private ArrayList<String> list;

    public int getCommand()
    {
        return command;
    }

    public void setCommand(int command)
    {
        this.command = command;
    }

    public Object getCommandData() {
        return commandData;
    }

    public void setCommandData(Object commandData) {
        this.commandData = commandData;
    }

    public Command(int command, Object commandData)
    {
        this.commandData = commandData;
        this.command = command;
    }

    public Command(int command)
    {
        this.command = command;
    }

    public Command(int command, ArrayList<String> list, Object commandData)
    {
        this.command = command;
        this.list = list;
        this.commandData = commandData;
    }
}