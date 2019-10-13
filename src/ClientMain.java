import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientMain {

    public static void main(String[] args)
    {

        try
        {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Enter the ip address of the server: ");
            String ip = keyboard.next();
            System.out.print("Enter your username: ");
            String username = keyboard.next();
//            System.out.println(""+username);
            Socket connectionToServer = new Socket(ip,8001);



            ObjectInputStream is = new ObjectInputStream(connectionToServer.getInputStream());
            ObjectOutputStream os = new ObjectOutputStream(connectionToServer.getOutputStream());

            os.writeObject(new Command(Command.JOIN, username));
            os.reset();

            Command c = (Command)is.readObject();

            if(c.getCommand()==c.JOIN)
            {
                System.out.println("logging in as " +username);
                Clients_Listener cl = new Clients_Listener(is, new Frame(username, os));
                Thread t = new Thread(cl);
                t.start();
                os.writeObject(new Command(Command.JOIN, username));
                os.reset();
            }

        }
        catch(Exception e)
        {
            System.out.println("Error in main: "+e.getMessage());
            e.printStackTrace();
        }
    }
}
