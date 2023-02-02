package bankmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class PinChange extends JFrame implements ActionListener{

    JPasswordField newPin,rePin;
    JButton b1,b2;                               
    JLabel text,newPintext,rePintext;
    String pinnum;
    
    PinChange(String pinnum)
    {
        this.pinnum = pinnum;
        
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 960, 1080);
        add(image);
        
        text = new JLabel("CHANGE YOUR PIN");
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setForeground(Color.WHITE);
        
        newPintext = new JLabel("New PIN:");
        newPintext.setFont(new Font("System", Font.BOLD, 16));
        newPintext.setForeground(Color.WHITE);
        
        rePintext = new JLabel("Re-Enter New PIN:");
        rePintext.setFont(new Font("System", Font.BOLD, 16));
        rePintext.setForeground(Color.WHITE);
        
        newPin = new JPasswordField();
        newPin.setFont(new Font("Raleway", Font.BOLD, 25));
        
        rePin = new JPasswordField();
        rePin.setFont(new Font("Raleway", Font.BOLD, 25));
        
        b1 = new JButton("CHANGE");
        b2 = new JButton("BACK");
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        text.setBounds(280,330,800,35);
        image.add(text);
        
        newPintext.setBounds(180,390,150,35);
        image.add(newPintext);
        
        rePintext.setBounds(180,440,200,35);
        image.add(rePintext);
        
        newPin.setBounds(350,390,180,25);
        image.add(newPin);
        
        rePin.setBounds(350,440,180,25);
        image.add(rePin);
        
        b1.setBounds(390,588,150,35);
        image.add(b1);
        
        b2.setBounds(390,633,150,35);
        image.add(b2);
        
        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }

    public static void main(String args[]) {
        new PinChange("");
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == b1)
        {
            try
            {
                String npin = newPin.getText();
                String rpin = rePin.getText();

                if(!npin.equals(rpin))
                {
                    JOptionPane.showMessageDialog(null, "Inccorect PIN!!");
                    return;
                }
                if(npin.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Enter BOTH PIN!!");
                    return;
                }
                if(rpin.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Enter BOTH PIN!!");
                    return;
                }    
                Conn c = new Conn();
                
                String query1 = "update bank set pin = '"+rpin+"' where pin = '"+pinnum+"'";
                String query2 = "update login set pin = '"+rpin+"' where pin = '"+pinnum+"'";
                String query3 = "update signupthree set pin = '"+rpin+"' where pin = '"+pinnum+"'";
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                c.s.executeUpdate(query3);
                
                JOptionPane.showMessageDialog(null, "Pin Changed");
                
                setVisible(false);
                new Transections(rpin).setVisible(true);
            }catch(Exception e)
            {
                System.out.println(e);
            }
        }
        else{
            setVisible(false);
            new Transections(pinnum).setVisible(true);
        }
    }
}
