import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Frame extends JFrame
{
    private JButton btn_exit 		= new JButton("Exit");
    private JButton btn_send 		= new JButton("Send");

    private JLabel lbl_users		= new JLabel("Users:");
    private JList list_users		= new JList();

    private JLabel lbl_chatBox		= new JLabel("Messages:");
    private JScrollPane	scr_chatBox	= null;
    private JTextArea txt_chatBox	= new JTextArea();

    private JLabel lbl_message		= new JLabel("Enter Message:");
    private JTextArea txt_message	= new JTextArea();


    private String userName			= "";
    private ArrayList<String> users = new ArrayList<String>();

    ObjectOutputStream os;
    public Frame(String userName,ObjectOutputStream os)
    {
        super("Chat Client");
        this.userName = userName;
        this.os = os;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,800);

        setResizable(false);
        list_users.setListData(users.toArray());
        list_users.setEnabled(false);
        lbl_users.setBounds(640,30,130,20);
        list_users.setBounds(640,50,130,550);


        scr_chatBox = new JScrollPane(txt_chatBox,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scr_chatBox.setBounds(20,50,600,550);
        lbl_chatBox.setBounds(20,30,100,20);
        txt_chatBox.setEditable(false);

        txt_message.setBounds(20,650,600,80);
        lbl_message.setBounds(20,630,100,20);

        btn_send.setBounds(640,650,130,30);
        btn_exit.setBounds(640,700,130,30);

        setLayout(null);
        add(txt_message);
        add(lbl_message);
        add(lbl_users);
        add(lbl_chatBox);
        add(scr_chatBox);
        add(list_users);
        add(btn_send);
        add(btn_exit);

        btn_exit.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        exit();
                    }
                }
        );

        btn_send.addActionListener(new java.awt.event.ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        sendtxt_message();
                    }
                }
        );


        setVisible(true);
    }

    public void exit()
    {
        System.exit(0);
    }

    public void sendtxt_message()
    {
        try
        {
            String m = userName+": "+txt_message.getText();
            os.writeObject(new Command(Command.SENT,m));
            os.reset();
            txt_message.setText("");
        }
        catch (Exception e)
        {
            System.out.println("Error in sending message: "+ e.getMessage());
            e.printStackTrace();
        }
        /*String m = userName + ": " + txt_message.getText();
        txt_chatBox.append(m+"\n");*/
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
        list_users.setListData(users.toArray());
        repaint();
    }

    public JTextArea getTxt_chatBox() {
        return txt_chatBox;
    }

    public void setTxt_chatBox(JTextArea txt_chatBox) {
        this.txt_chatBox = txt_chatBox;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}