package org.example;

import java.sql.*;
public class Main {
    public static void main(String[] args) throws SQLException {

//        readRecords();
//        insertRecords();
//        insertRecordByPreparedStatement();
//            deleteRecord();
//            updateRecord();
            updateRecordByPreparedStatement();
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

    public static void insertRecords() throws SQLException{

        String url = "jdbc:mysql://localhost:3306/jdbcdemotry";
        String userName = "root";
        String password ="";

        String insertDataToEmployeeTable = "insert into employee values(5,'Sharma',50000)";

        Connection connection = DriverManager.getConnection(url,userName,password);     //establish connection
        Statement statement = connection.createStatement();   // used to execute SQL statements by using statement object
        int rows = statement.executeUpdate(insertDataToEmployeeTable);      // to execute sql query and produce sql output in int form(how many rows get affected)
         // if we want to modify DB then we use this executeUpdate method

        System.out.println("Number of rows get affected : "+rows);



        connection.close();
    }

    public static void insertRecordByPreparedStatement() throws SQLException{

        String url = "jdbc:mysql://localhost:3306/jdbcdemotry";
        String userName = "root";
        String password ="";

        int id=6;
        String name="Ragu";
        int salary=45000;

        String insertDataToEmployeeTable = "insert into employee values(?,?,?)";

        Connection connection = DriverManager.getConnection(url,userName,password);

        PreparedStatement preparedStatement = connection.prepareStatement(insertDataToEmployeeTable);
                    // Set the values for each placeholder
        preparedStatement.setInt(1,id);     // assigning values to ?  // need to consider datatype
        preparedStatement.setString(2,name);
        preparedStatement.setInt(3,salary);

        int rows =  preparedStatement.executeUpdate();
        System.out.println("Number of rows get affected : "+rows);

        connection.close();

    }

    public static void deleteRecord() throws SQLException{

        String url = "jdbc:mysql://localhost:3306/jdbcdemotry";
        String userName = "root";
        String password ="";

        int id=6;

        String deleteFromEmployeeTable = "delete from employee where emp_id="+id;

        Connection connection = DriverManager.getConnection(url,userName,password);
        Statement statement =connection.createStatement();
        int rows =  statement.executeUpdate(deleteFromEmployeeTable);
        System.out.println("Number of rows get deleted : "+rows);

        connection.close();

    }


    public static void updateRecord() throws SQLException{

        String url = "jdbc:mysql://localhost:3306/jdbcdemotry";
        String userName = "root";
        String password ="";

        String updateEmployeeTable = "update employee set salary= 35450 where emp_id=3";

        Connection connection = DriverManager.getConnection(url,userName,password);
        Statement statement =connection.createStatement();
        int rows =  statement.executeUpdate(updateEmployeeTable);
        System.out.println("Number of rows get updated : "+rows);

        connection.close();

    }

    public static void updateRecordByPreparedStatement() throws SQLException{

        String url = "jdbc:mysql://localhost:3306/jdbcdemotry";
        String userName = "root";
        String password ="";

        int id=2;
        String name="Ramya";
        int salary=36525;

        String updateEmployeeTable = "update employee set ename =? ,salary= ? where emp_id="+id;

        Connection connection = DriverManager.getConnection(url,userName,password);

        PreparedStatement preparedStatement = connection.prepareStatement(updateEmployeeTable);
        preparedStatement.setString(1,name);     // assigning values to ?  // need to consider datatype
        preparedStatement.setInt(2,salary);

        int rows =  preparedStatement.executeUpdate();   //Execute the update
        System.out.println("Number of rows get affected : "+rows);

        connection.close();

    }

}