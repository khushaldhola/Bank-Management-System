package bankmanagementsystem;

import javax.swing.*;                   //to make frame, extended version of java so javax
import java.awt.*;  
import java.awt.event.*;
import java.sql.*;

//to performs the actions that after clicking this button what should be done so that action we have to implements an interface "ActionListener" inside java.awt.event
//Login is not abstract and does not override abstract method actionPerformed(ActionEvent) in ActionListener, so we need to override it.
public class Login extends JFrame implements ActionListener                                             //so to make frame we need to inherite that class
{
    JButton login, signUp, clear;                                                                       //defined globally so we can access them outside of the constructor globally.
    JTextField cardTextField;
    JPasswordField pinTextField;                                                                        //using ''JPasswordField'' we can't see password instead of actuall text we see *** in that field
    
    Login(){                            //constructor
        setTitle("Testing");
        
        setLayout(null);
        
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));        //to place image we require class, to take that img we have classloader class and in that we have a static func of getsysreso.. and pasa address
        Image img2 = img1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);     //to scale the image(make an ibject of Image class), get image with .getImage method, with scale method we make it right,(Image class is not present in swing class so, we need 'awt' package)
        ImageIcon img3 = new ImageIcon(img2);                                                         //here we can place img1(Image) to Jlabel, but cannot gave img2 so we need to convet Image into ImageIcon, now we can pass into JLabel
        JLabel label = new JLabel(img3);                                                              //we cannot place an icon directaly inside a frame so we need to place it in JLabel
        label.setBounds(70, 10, 100, 100);                                                  //we need to adjust our label in frame with setBounds method, still not work so here we have layouts to place components (byDefault - border layout(and in border layout properties is center))
        //so i don't won't your layouts i wanna build my costum layout, for that we use setBound method, so first we need to set layout as a null, now it will take our costom layout no the border layout.
    
        add(label);                                                                                   //to place any components in the frame, and pass an object
        
        JLabel text = new JLabel("Welcome To ATM");                                                   //to write anything in the frame we can also use JLabel
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200, 50, 400, 40);                                                  //it not shows so manually we set them using setBounds, if our label size is lesser than the actual texts than it shows ... after some characters
        add(text);
        
        JLabel cardno = new JLabel("Card no : ");
        cardno.setFont(new Font("Raleway", Font.BOLD, 28));
        cardno.setBounds(120, 150, 150, 30);
        add(cardno);
        
        //JTextField cardTextField = new JTextField();                                                       //user can entry using TextField and it's a String so add J
        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);                                         //we also need to make textfield as a global beacause in clear action we need to access those fields
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);
        
        JLabel pin = new JLabel("Pin : ");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 220, 250, 30);
        add(pin);
        
        pinTextField = new JPasswordField();                                                            //we declared JPasswordField globally to write the password in this field
        pinTextField.setBounds(300, 220, 230, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);
        
        //JButton login = new JButton("SIGN IN");                                                       //make an button
        login = new JButton("SIGN IN");                                                             //beacause we declared it globally.
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.white);
        login.addActionListener(this);                                                                   //it's an listener
        add(login);
        
        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.white);
        clear.addActionListener(this);
        add(clear);
        
        signUp = new JButton("SIGN UP");
        signUp.setBounds(300, 350, 230, 30);
        signUp.setBackground(Color.BLACK);
        signUp.setForeground(Color.white);
        signUp.addActionListener(this);
        add(signUp);
        
        getContentPane().setBackground(Color.WHITE);                                                    //suppose i want to change the color of the whole frame so need to take whole frame - getContentPane();
        
        setSize(800, 480);   //to set frame
        setVisible(true);            //by default frame is hidden from user visibility
        setLocation(350, 200);     //it opens in top-left(ByDefault frame location)
    }
    public static void main(String args[])
    {
        new Login();
    }

    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //,if we not write this than also it works it is just warning
        //we set the listener, now we write here that what action should be performed
        //buttons are defined locally, so we need to make them global
        if(e.getSource() == clear)                                                                        //which button is clicked, what source of it
        {
            //cardTextField.setText("This is Written");                                                  //to manually set, so whenever we press clear than this text is written in that field
            cardTextField.setText("");
            pinTextField.setText("");
        }
        else if(e.getSource() == login)
        {
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();                          //here --- is beacause our pin is password field type but we also can use gettext, it works
            String query = "select * from login where cardnumber = '"+cardnumber+"' and pin = '"+pinnumber+"'";
            try{
                ResultSet rs = conn.s.executeQuery(query);                //in sql library(DDL cmnd so executeQuery, not the update), and here type of data is ResultSet which stores info of query
                if(rs.next())                                                   //rs.next -- data is returned
                {
                    setVisible(false);
                    new Transections(pinnumber).setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Incorect card number OR pin");
                }
            }catch(Exception ee){
                System.out.println(ee);
            }
            
            
        }
        else if(e.getSource() == signUp)
        {
            setVisible(false);                                                //suppose we close login frame so to do that
            new Signup_1().setVisible(true);                                  //with signup obj we opens that frame
        }
    }
}
