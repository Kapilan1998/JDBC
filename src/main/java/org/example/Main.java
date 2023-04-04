package org.example;

import java.sql.*;
public class Main {
    public static void main(String[] args) throws SQLException {

        readRecords();


    }

    public static void readRecords() throws SQLException{

        String url = "jdbc:mysql://localhost:3306/jdbcdemotry";
        String userName = "root";
        String password ="";

        String readQueryFromEmployeeTable = "select * from employee";

        Connection connection = DriverManager.getConnection(url,userName,password);     //establish connection
        Statement statement = connection.createStatement();   // used to execute SQL statements by using statement object
        ResultSet resultSet = statement.executeQuery(readQueryFromEmployeeTable);      // to execute sql query and produce sql output in resultset form
        // so we need to create object for Resultset and then only we can see output of sql query
        //whenever we want to read data only from DB and not going to change then we can use executeQuery
        // if we want to modify DB then we can't use this executeQuery method
        
        System.out.println("displaying employee details.......");

        // while loop is used to read all data in DB
        while(resultSet.next()){  // need to move the pointer to read next data
            System.out.println("Id is " + resultSet.getInt(1));      // need to read each data in table column as it is 1st colum we are putting as 1
            System.out.println("Name is " + resultSet.getString(2));
            System.out.println("Salary is " + resultSet.getInt(3));
            System.out.println("  ");
        }

        System.out.println("displaying gpa details.......");

        String readQueryFromGpaTable ="select * from gpa";

        ResultSet resultSet1 = statement.executeQuery(readQueryFromGpaTable);
        while(resultSet1.next()){
            System.out.println("Id is "+ resultSet1.getInt(1));
            System.out.println("Name is "+ resultSet1.getString(2));
            System.out.println("Gpa is "+resultSet1.getString(3));
        }

        connection.close();
    }
}