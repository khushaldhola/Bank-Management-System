package bankmanagementsystem;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener{
    
    JLabel l1;
    JButton b1,b2,b3,b4,b5,b6,b7;
    String pin;
    
    FastCash(String pin)
    {
        this.pin = pin;
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 960, 1080);
        add(image);
        
        l1 = new JLabel("Select Withdrawl Amount");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));
        l1.setBounds(210,300,700,35);
        image.add(l1);                                                      //to add text upper the image layer
        
        b1 = new JButton("Rs 100");
        b2 = new JButton("Rs 500");
        b3 = new JButton("Rs 1000");
        b4 = new JButton("Rs 2000");
        b5 = new JButton("Rs 5000");
        b6 = new JButton("Rs 10000");
        b7 = new JButton("BACK");
        
        l1.setBounds(235,400,700,35);
        image.add(l1);
        
        b1.setBounds(170,499,150,35);
        image.add(b1);
        
        b2.setBounds(390,499,150,35);
        image.add(b2);
        
        b3.setBounds(170,543,150,35);
        image.add(b3);
        
        b4.setBounds(390,543,150,35);
        image.add(b4);
        
        b5.setBounds(170,588,150,35);
        image.add(b5);
        
        b6.setBounds(390,588,150,35);
        image.add(b6);
        
        b7.setBounds(390,633,150,35);
        image.add(b7);
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(900, 900);
        setLocation(300,0);
        setUndecorated(true);                                          //removes our closing bar only frame is visible
        setVisible(true);
    }
    
    public static void main(String args[]) {
        new FastCash("");
    }

    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource()==b7){ 
            setVisible(false);
            new Transections(pin).setVisible(true);
        }
        else
        {
            String amount = ((JButton)ae.getSource()).getText().substring(3);                //instead of making if-else tree we can directly get amount (here, getSource(return obj so typecasted in button) gives what is the source of it and then we get how much money is that, and also rs is there while we get text so SubString is useful)
            //.substring(3) removes first three lettors
            Conn c = new Conn();
            try
            {
                ResultSet rs = c.s.executeQuery("select * from bank where pin = '"+pin+"' ");               //in sql package
                int balance = 0;
                while(rs.next())                                                //to loop the every row.
                {
                    if(rs.getString("type").equals("Deposit")){
                        balance += Integer.parseInt(rs.getString("amount"));
                    }
                    else{
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }
                if(ae.getSource()!=b7 && balance < Integer.parseInt(amount)){ 
                    JOptionPane.showMessageDialog(null, "Deposit toh karley pahele.");
                    return;
                }
                Date date = new Date();
                String query = "insert into bank values('"+pin+"','"+date+"', 'Withdrawl', '"+amount+"')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited");
                
                setVisible(false);
                new Transections(pin).setVisible(true);
            }catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }
}
