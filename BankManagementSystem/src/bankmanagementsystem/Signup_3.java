package bankmanagementsystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.sql.*;

public class Signup_3 extends JFrame implements ActionListener{
    
    JLabel accountDetails, aType, cardNum, cardNum1, pin, pinNum, services;
    JRadioButton aT1, aT2, aT3, aT4;
    JCheckBox c1, c2, c3, c4, c5;
    JButton submit, cancel;
    String formno;
    
    Signup_3(String formno)
    {
        System.out.print(formno);
        this.formno = formno;
        setLayout(null);
        
        setTitle("Account Details - Page 3");
        
        
        accountDetails = new JLabel("Page 3: Account Details");
        accountDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        accountDetails.setBounds(290,40,400,30);
        add(accountDetails);
        
        aType = new JLabel("Account Type");
        aType.setFont(new Font("Raleway", Font.BOLD, 22));
        aType.setBounds(100,150,400,30);
        add(aType);
        
        aT1 = new JRadioButton("Saving Account");
        aT1.setBounds(100, 180, 200, 30);
        aT1.setBackground(Color.WHITE);
        add(aT1);
        
        aT2 = new JRadioButton("Fixed Deposit Account");
        aT2.setBounds(300, 180, 200, 30);
        aT2.setBackground(new Color(255,255,255));
        add(aT2);
        
        aT3 = new JRadioButton("Current Account");
        aT3.setBounds(100, 210, 200, 30);
        aT3.setBackground(Color.WHITE);
        add(aT3);
        
        aT4 = new JRadioButton("Recurring Deposit Account");
        aT4.setBounds(300, 210, 200, 30);
        aT4.setBackground(new Color(255,255,255));
        add(aT4);
        
        //both of the radio buttons are selectiong so to make that only one can be selected we have to make group of them
        ButtonGroup accGroup = new ButtonGroup();
        accGroup.add(aT1);
        accGroup.add(aT2);
        accGroup.add(aT3);
        accGroup.add(aT4);
        
        cardNum = new JLabel("Card Number");
        cardNum.setFont(new Font("Raleway", Font.BOLD, 22));
        cardNum.setBounds(100,270,200,30);
        add(cardNum);
        
        cardNum1 = new JLabel("XXXX-XXXX-XXXX-4184");
        cardNum1.setFont(new Font("Raleway", Font.BOLD, 22));
        cardNum1.setBounds(350,270,300,30);
        add(cardNum1);
        
        pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 22));
        pin.setBounds(100,320,200,30);
        add(pin);
        
        pinNum = new JLabel("XXXX");
        pinNum.setFont(new Font("Raleway", Font.BOLD, 22));
        pinNum.setBounds(350,320,300,30);
        add(pinNum);
        
        services = new JLabel("Services Recured");
        services.setFont(new Font("Raleway", Font.BOLD, 22));
        services.setBounds(100,450,200,30);
        add(services);
        
        c1 = new JCheckBox("ATM card");
        c1.setBackground(Color.WHITE);
        c1.setFont(new Font("Raleway", Font.BOLD, 16));
        c1.setBounds(100,500,200,30);
        add(c1);
        
        c2 = new JCheckBox("Internet Banking");
        c2.setBackground(Color.WHITE);
        c2.setFont(new Font("Raleway", Font.BOLD, 16));
        c2.setBounds(350,500,200,30);
        add(c2);
        
        c3 = new JCheckBox("SMS Alerts");
        c3.setBackground(Color.WHITE);
        c3.setFont(new Font("Raleway", Font.BOLD, 16));
        c3.setBounds(100,550,200,30);
        add(c3);
        
        c4 = new JCheckBox("Chequebook");
        c4.setBackground(Color.WHITE);
        c4.setFont(new Font("Raleway", Font.BOLD, 16));
        c4.setBounds(350,550,200,30);
        add(c4);
        
        c5 = new JCheckBox("here by i declare that i read all the Conditions even there is not a single one presented?");
        c5.setBackground(Color.WHITE);
        c5.setFont(new Font("Raleway", Font.BOLD, 12));
        c5.setBounds(100,680,600,30);
        add(c5);
        
        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.white);
        submit.setFont(new Font("Arial", Font.BOLD, 14));
        submit.setBounds(220, 720, 100, 30);
        submit.addActionListener(this);
        add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.white);
        cancel.setFont(new Font("Arial", Font.BOLD, 14));
        cancel.setBounds(420, 720, 100, 30);
        cancel.addActionListener(this);
        add(cancel);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(850, 800);
        setLocation(350,10);
        setVisible(true);
    }
    public static void main(String []args)
    {
        new Signup_3("");
    }


    public void actionPerformed(ActionEvent ae) {
        
            String accountType = null;
            if(aT1.isSelected())
                accountType = "Saving Account";
            else if(aT2.isSelected())
                accountType = "Fixed Deposit Account";
            else if(aT3.isSelected())
                accountType = "Current Account";
            else if(aT4.isSelected())
                accountType = "Recurring Deposit Account";
            
            Random random = new Random();
            String cardNumber = "" + (Math.abs((random.nextLong() % 90000000L) + 5040936000000000L));       //5040936 is the initial of any number
            String number = "" + (Math.abs((random.nextLong() % 9000L) + 1000L));
            
            String facility = "";       //so we can select multiple(we concates them)
            if(c1.isSelected())
                facility = facility + " ATM card";
            else if(c2.isSelected())
                facility = facility + " Internet Banking";
            else if(c3.isSelected())
                facility = facility + " SMS Alerts";
            else if(c4.isSelected())
                facility = facility + " Chequebook";
            else if(c5.isSelected())
                facility = facility + " K-Statement";
            
            try{
                if(ae.getSource()==submit)                                                                        //which button is clicked, what source of it
                {
                    if(accountType.equals(""))
                        JOptionPane.showMessageDialog(null, "Please enter  Acc Type");
                    else{
                        Conn conn = new Conn();
                        //create table signupthree(formno varchar(20), accounType varchar(40), cardnumber varchar(20), pin varchar(20), facility varchar(200))
                        String q1 = "insert into signupthree values('"+formno+"', '"+accountType+"', '"+cardNumber+"', '"+number+"', '"+facility+"')";
                        String q2 = "insert into login values('"+formno+"','"+cardNumber+"', '"+number+"')";

                        conn.s.executeUpdate(q1);
                        conn.s.executeUpdate(q2);
                        //create table login(formno varchar(20), cardnumber varchar(25), pin varchar(10));
                        JOptionPane.showMessageDialog(null, "Card Number: " + cardNumber + "\nPin: " + number);
                    }
                    setVisible(false);
                    new Deposit(number).setVisible(true);
                }
                else if(ae.getSource() == cancel)
                {
                    setVisible(false);
                    new Login().setVisible(true);
                }
                
            }catch(Exception e){
                System.out.println(e);
            }
        
        
    }
}
