import java.io.*;
import java.net.*;

public class ServerMain {

    public static void main(String[] args)
    {

        try
        {
            ServerSocket serverSocket = new ServerSocket(8001);
            while(true)
            {
                Socket socket = serverSocket.accept();
                ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

                Command v = (Command)is.readObject();
                if(v.getCommand()==Command.JOIN && !Servers_Listener.users.contains(v.getCommandData()))
                {
                    os.writeObject(new Command(Command.JOIN,v.getCommandData()));
                    os.reset();
                }
                Thread t = new Thread(new Servers_Listener(os,is));
                t.start();
            }




        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
    }


}
