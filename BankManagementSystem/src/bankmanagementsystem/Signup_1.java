package bankmanagementsystem;

import java.awt.*;
import javax.swing.*;
import java.util.*;                                                             //beacause of random
import com.toedter.calendar.JDateChooser;                                       //for date selaction
import java.awt.event.*;

public class Signup_1 extends JFrame implements ActionListener{
    
    long random;
    JTextField nameTextField, emailTextField, stateTextField;
    JDateChooser dateChooser;
    JButton next;
    JRadioButton male, female;
    
    Signup_1()
    {
        
        setLayout(null);
        
        Random ran = new Random();      //generates random number
        random = (Math.abs((ran.nextLong() % 9000L) + 1000L));
        //System.out.println(Math.abs((ran.nextLong() % 9000L) + 1000L));                   //gives 4 digit  +ve random number
        
        JLabel formno = new JLabel("Application Form No." + random);
        formno.setFont(new Font("Raleway", Font.BOLD, 38));
        formno.setBounds(140,20,600,40);
        add(formno);
        
        JLabel personDetails = new JLabel("Page 1: Pesonal Details");
        personDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        personDetails.setBounds(290,80,400,30);
        add(personDetails);
        
        JLabel name = new JLabel("Name:");
        name.setFont(new Font("Raleway", Font.BOLD, 22));
        name.setBounds(100,140,200,30);
        add(name);
        
        nameTextField = new JTextField();
        nameTextField.setBounds(300, 140, 400, 30);
        nameTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(nameTextField);
        
        JLabel dob = new JLabel("D.O.B.:");                                 //for the date of the birth we have to need a jar file (in netBeans -> in left section (Projects) -> Right Click -> add Jar/Folder -> and select that file that we want, now we have that file in library, and import an package)
        dob.setFont(new Font("Raleway", Font.BOLD, 22));
        dob.setBounds(100,190,200,30);
        add(dob);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(300,190,400,30);
        dateChooser.setForeground(new Color(255,255,255));
        add(dateChooser);
        
        JLabel gender = new JLabel("Gender:");
        gender.setFont(new Font("Raleway", Font.BOLD, 22));
        gender.setBounds(100,240,200,30);
        add(gender);
        
        male = new JRadioButton("Male");
        male.setBounds(300, 240, 200, 30);
        male.setBackground(Color.WHITE);
        add(male);
        
        female = new JRadioButton("FeMale");
        female.setBounds(500, 240, 200, 30);
        female.setBackground(new Color(255,255,255));
        add(female);
        
        //both of the radio buttons are selectiong so to make that only one can be selected we have to make group of them
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        
        JLabel email = new JLabel("E.Mail:");
        email.setFont(new Font("Raleway", Font.BOLD, 22));
        email.setBounds(100,290,200,30);
        add(email);
        
        emailTextField = new JTextField();
        emailTextField.setBounds(300, 290, 400, 30);
        emailTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(emailTextField);
        
        JLabel state = new JLabel("State:");
        state.setFont(new Font("Raleway", Font.BOLD, 22));
        state.setBounds(100,340,200,30);
        add(state);
        
        stateTextField = new JTextField();
        stateTextField.setBounds(300, 340, 400, 30);
        stateTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(stateTextField);
        
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
        //we hav eonly one button so no need of if-else
        String formno = "" + random;                    //it is in form of long and we need str to store in database so we need to convert it (if we concate anything with "" that it will convert in str type)
        String name = nameTextField.getText();          //getting name that present in nameTextField using getText() function
        String email = emailTextField.getText();
        String state = stateTextField.getText();
        String dob = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();      //so we need to convert it into str then we can get
        String gender = null;
        if(male.isSelected()){          //we need to check that which thing is selected
            gender = "male";
        }
        else{
            gender = "female";
        }
        
        //now we want to hit database, and cause it is external entity so try...
        try
        {
            if(name.equals("")){
                JOptionPane.showMessageDialog(null, "name Required");                               //we can show userialog box
            }
            else{
                Conn c1 = new Conn();            //to enstablished connection to the database
                String q1 = "insert into signup values('"+formno+"', '"+name+"', '"+dob+"', '"+gender+"', '"+email+"', '"+state+"')";    //creating a sql command
//                c.s.executeUpdate(query);           //to excecute the query, it is a dml query
                c1.s.executeUpdate(q1);
                //in mysql to create table -- create table signup(formno varchar(20), name varchar(20), dob varchar(20), gender varchar(20), email varchar(20), state varchar(20),)
                //show tables; select * from signup;
                
                //if user succesfully entered data than we pass it to the next page
                setVisible(false);
                new Signup_2(formno).setVisible(true);                          //it diasable page-1 view and show us the second page, and we pass form no so we can access it in the page two
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    public static void main(String []args)
    {
        new Signup_1();
    }
}
