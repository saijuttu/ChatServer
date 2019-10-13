import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.ArrayList;
public class Servers_Listener implements Runnable{

    private ObjectInputStream is;
    private char player;
    private static ArrayList<ObjectOutputStream> osList = new ArrayList<ObjectOutputStream>();
    public static ArrayList<String> users = new ArrayList<String>();
    private ObjectOutputStream os;


    public Servers_Listener(ObjectOutputStream os, ObjectInputStream is)
    {
        this.is			= is;
        this.os			= os;
        osList.add(os);
    }

    public ObjectInputStream getIS()
    {	return is;	}

    public static ArrayList<ObjectOutputStream> getOSList()
    {	return osList;	}

    public void run()
    {
        try
        {
            while(true)
            {
                Command x = (Command)is.readObject();

                if(x.getCommand() == x.JOIN)
                {
                    users.add(0, (String)x.getCommandData());
                    for(ObjectOutputStream out: osList)
                    {
                        Command c = new Command(Command.JOIN,users);
                        out.writeObject(c);
                        out.reset();
                    }
                }
                if(x.getCommand()==x.LOGOFF)
                {
                    users.remove((String)x.getCommandData());
                    for(ObjectOutputStream out: osList)
                    {
                        Command c = new Command(Command.LOGOFF,users);
                        out.writeObject(c);
                        out.reset();
                    }
                }
                if(x.getCommand() == x.SENT)
                {
                    for(int d = 0;d < osList.size();d++)
                    {
                        Command c = new Command(Command.SENT,x.getCommandData());
                        osList.get(d).writeObject(c);
                        osList.get(d).reset();
                    }

                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error in Server's Listener: "+ e.getMessage());
            e.printStackTrace();
        }

    }
}
