package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener{
    
    JTextField t1;
    JButton withdraw, back;
    JLabel l1;
    String pin;
    
    Withdrawl(String pin)
    {
        this.pin = pin;
        
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 980, 1080);
        add(image);
        
        l1 = new JLabel("ENTER AMOUNT YOU WANT TO WITHDRAW");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 22));
        
        withdraw = new JButton("WITHDRAW");
        back = new JButton("BACK");
        
        l1.setBounds(190,350,400,35);
        image.add(l1);
        
        t1.setBounds(190,420,320,25);
        image.add(t1);
        
        withdraw.setBounds(390,588,150,35);
        image.add(withdraw);
        
        back.setBounds(390,633,150,35);
        image.add(back);
        
        withdraw.addActionListener(this);
        back.addActionListener(this);
        
        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }
    
    public static void main(String args[]) {
        new Withdrawl("");
    }

    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == withdraw)
        {
            //create table bank(pin varchar(10), date varchar(25), type varchar(10), amount varchar(10));
            String amount = t1.getText();
            Date date = new Date();
            if(amount.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Enter Amount.");
            }
            else
            {
                try {
                    Conn conn = new Conn();
                    String query = "insert into bank values('"+pin+"','"+date+"', 'Withdrawl', '"+amount+"')";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Rs. " +amount+ " Withdrawed succesfully");
                    setVisible(false);
                    new Transections(pin).setVisible(true);
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }
        else if(ae.getSource() == back)
        {
            setVisible(false);
            new Transections(pin).setVisible(true);
        }
    }
}
