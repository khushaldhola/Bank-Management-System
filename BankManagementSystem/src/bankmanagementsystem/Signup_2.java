package bankmanagementsystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Signup_2 extends JFrame implements ActionListener{
    
    JTextField aadharTextField;
    JComboBox religion, income, edu;
    JButton next;
    String formno;
    
    //Signup_2(String formn)
    Signup_2(String formno)        
    {
        //this.formno = formn;
        this.formno = formno;
        
        setLayout(null);
        
        setTitle("New Account Application Form - Page 2");
        
        
        JLabel AdditionalDetails = new JLabel("Page 2: Additional Details");
        AdditionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        AdditionalDetails.setBounds(290,80,400,30);
        add(AdditionalDetails);
        
        JLabel name = new JLabel("Religion:");
        name.setFont(new Font("Raleway", Font.BOLD, 22));
        name.setBounds(100,140,200,30);
        add(name);
        
        String valReligion[] = {"Hindu", "Muslim", "shikh"};                    //we create a drop down menu for this
        religion= new JComboBox(valReligion);
        religion.setBounds(300,140,400,30);
        religion.setBackground(Color.WHITE);
        add(religion);
        
        JLabel dob = new JLabel("Income:");
        dob.setFont(new Font("Raleway", Font.BOLD, 22));
        dob.setBounds(100,190,200,30);
        add(dob);
        
        String incomeCategory[] = {"Null", "< 1,50,000", "< 2,50,000", "> 2,50,000"};
        income= new JComboBox(incomeCategory);
        income.setBounds(300,190,400,30);
        income.setBackground(Color.WHITE);
        add(income);
        
        JLabel gender = new JLabel("Education:");
        gender.setFont(new Font("Raleway", Font.BOLD, 22));
        gender.setBounds(100,240,200,30);
        add(gender);
        
        String eduQualification[] = {"Non-Graduate", "Graduate", "Post-Graduate", "Others"};                    //we create a drop down menu for this
        edu= new JComboBox(eduQualification);
        edu.setBounds(300,240,400,30);
        edu.setBackground(Color.WHITE);
        add(edu);
        
        JLabel aadhar = new JLabel("Aadhar number:");
        aadhar.setFont(new Font("Raleway", Font.BOLD, 22));
        aadhar.setBounds(100,340,200,30);
        add(aadhar);
        
        aadharTextField = new JTextField();
        aadharTextField.setBounds(300, 340, 400, 30);
        aadharTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(aadharTextField);
        
        next = new JButton("Next");
        next.setBounds(300, 550, 230, 30);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.white);
        next.setFont(new Font("Arial", Font.BOLD, 14));
        next.setBounds(620, 660, 80, 30);
        next.addActionListener(this);
        add(next);
        
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(850, 800);
        setLocation(350,10);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        
        String aadhar = aadharTextField.getText();
        String sreligion = (String)religion.getSelectedItem();                          //to get items from drop-down menu but this returns an obj so we need to typpe cast it
        String sincome = (String)income.getSelectedItem();
        String sedu = (String)edu.getSelectedItem();
        
        try
        {
            Conn c1 = new Conn();
            //create table signuptwo(formno varchar(20), religion varchar(20), income varchar(20), edu varchar(20));
            String q1 = "insert into signuptwo values('"+formno+"', '"+sreligion+"', '"+sincome+"', '"+sedu+"')";
            c1.s.executeUpdate(q1);
            
            //signup_3 object
            setVisible(false);
            new Signup_3(formno).setVisible(true);
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    public static void main(String []args)
    {
        new Signup_2("");                                                  //we don't have any default constructor so we passed an empty string
    }
}
