import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Clients_Listener implements Runnable
{
    private ObjectInputStream is;
    private Frame frame;

    public Clients_Listener(ObjectInputStream is,Frame frame)
    {
        this.is		=is;
        this.frame 	= frame;
    }

    public ObjectInputStream getIS()
    {	return is;	}

    public Frame getFrame()
    {	return frame;	}

    public void run()
    {
        try
        {
            while(true)
            {
                Command x =(Command)is.readObject();
                if(x.getCommand()==x.LOGOFF)
                {
                    frame.setUsers((ArrayList<String>) x.getCommandData());
                    frame.getTxt_chatBox().append("\n"+x.getCommandData()+" has disconnected.");

                }
                if(x.getCommand() == x.JOIN)
                {
                    frame.setUsers((ArrayList<String>) x.getCommandData());
                    frame.getTxt_chatBox().append("\n"+((ArrayList<String>) x.getCommandData()).get(0)+" has joined.");
                }
                if(x.getCommand() == x.SENT)
                {
                    frame.getTxt_chatBox().append("\n"+x.getCommandData());
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error in Clients Listener: "+ e.getMessage());
            System.out.println("Lost Connection to the Server.");
            System.out.println("Disconnecting");
            e.printStackTrace();
            System.exit(0);
        }

    }
}