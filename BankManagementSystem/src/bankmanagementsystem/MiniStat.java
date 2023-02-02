package bankmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class MiniStat extends JFrame implements ActionListener{

    JButton b1, b2;
    JLabel mini;
    MiniStat(String pin){
        super("Mini Statement");                                            //work as title
        setLayout(null);   

        mini = new JLabel();                                                    //Dynamic
        add(mini);
        mini.setBounds(20, 140, 400, 200);
        
        JLabel bank = new JLabel("Indian Bank");
        bank.setBounds(150, 20, 100, 20);
        add(bank);
        
        JLabel card = new JLabel();
        card.setBounds(20, 80, 300, 20);
        add(card);
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from login where pin = '"+pin+"'");
            
            while(rs.next()){
                card.setText("Card Number:    " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12));//with settext we send values in label
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        JLabel l4 = new JLabel();
        l4.setBounds(20, 400, 300, 20);
        add(l4);
        
        try{
            int balance = 0;
            Conn c2  = new Conn();
            ResultSet rs = c2.s.executeQuery("SELECT * FROM bank where pin = '"+pin+"'");
            while(rs.next()){
                //setText overWrites, so to append, we can use html tag with java &nbsp; - to add space in html
                mini.setText(mini.getText() + "<html>"+rs.getString("date")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
                if(rs.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(rs.getString("amount"));
                }else{
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
            l4.setText("Your total Balance is Rs "+balance);
        }catch(Exception e){
            e.printStackTrace();
        }

        b1 = new JButton("Exit");
        add(b1);
        b1.addActionListener(this);
        b1.setBounds(20, 500, 100, 25);
        
        getContentPane().setBackground(Color.WHITE);
        setSize(400,600);
        setLocation(20,20);
        setVisible(true);   
    }
    public static void main(String args[]) {
        new MiniStat("");
    }

    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
    }
}