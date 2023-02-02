//here we enstablished connection with database, we don't require main because we use this calsss as a object.
/*
JDBC (Java Database Connectivity) Steps

1. Register The Driver
2. Create Connection
3. Create Statement
4. Execute Query
5. Close Connection
*/
package bankmanagementsystem;

import java.sql.*;

public class Conn
{
    Connection c;
    Statement s;        //declared statement before step 3
        
    public Conn()
    {
        
        //Exception Handling(Mysql is an external entity so chances of run-time ERROR(not the compile time error) so to catch those errors)
        try{
            //Class.forName(com.mysql.cj.jdbc.Driver);                    //1. Register The Driver( The forName() static method of Class class is used to register the driver class. This method is used to dynamically load the driver class., and enetr the driver name, we need to import java.sql Library )
            //we don't need to register driveruto find that path, so we can comment upper line
            
            c =  DriverManager.getConnection("jdbc:mysql:///bankManagementSystem", "root", "khushal@D1344");        //2. Create Connection (we maked connection string)
           
            //c = DriverManager.getConnection("jdbc:mysql://(ByDefault -> localhot:3306)/(DataBase Name)", "mysql-username"(if manually not changed than root),"password")
            //now in mysql workbench create database (create database bankManagementSystem; show databases; use bankManagementSystem;)
            
            s = c.createStatement();        //3. Create Statement, with use of connection
            // com.mysql.cj.jdbc.Driver -- this classname gives error so we need to import that library(our this drive is in that library named as mysql-connector-java)
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
