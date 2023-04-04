package org.example;

import java.sql.*;
public class Main {
    public static void main(String[] args) throws SQLException {

//        readRecords();
//        insertRecords();
//        insertRecordByPreparedStatement();
//            deleteRecord();
//            updateRecord();
//            updateRecordByPreparedStatement();

//        commitDemo();
        batchProcessDemo();




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

                // commit vs autocommit
    public static void commitDemo() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/jdbcdemotry";
        String userName = "root";
        String password ="";

        String updateEmployee1 = "update employee set salary= 11051 where emp_id=3";
        String updateEmployee2 = "update employee set salary= 6513 where emp_id=5";

        Connection connection = DriverManager.getConnection(url,userName,password);
        connection.setAutoCommit(false);    // execute all methods if they are true
                // if any method didnot execute then it will revoke completely

        Statement statement =connection.createStatement();
        int row1 =  statement.executeUpdate(updateEmployee1);
        System.out.println("Number of rows get updated : "+row1);

        int row2 =  statement.executeUpdate(updateEmployee2);
        System.out.println("Number of rows get updated : "+row2);

        if (row1>0 && row2>0)
            connection.commit();    // if both condition true then execute update query

        connection.close();
    }

                // execute more tasks in 1 time
    public static void batchProcessDemo() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/jdbcdemotry";
        String userName = "root";
        String password ="";

        String updateEmployee1 = "update employee set salary= 1001 where emp_id=1";
        String updateEmployee2 = "update employee set salary= 1002 where emp_id=2";
        String updateEmployee3 = "update employee set salary= 1003 where emp_id=3";
//        String updateEmployee4 = "update employee set salary= 1004 where emp_id=4";
        String updateEmployee5 = "update employee set salary= 1005 where emp_id=5";

        Connection connection = DriverManager.getConnection(url,userName,password);
        connection.setAutoCommit(false);
        Statement statement =connection.createStatement();
        statement.addBatch(updateEmployee1);
        statement.addBatch(updateEmployee2);
        statement.addBatch(updateEmployee3);
//        statement.addBatch(updateEmployee4);
        statement.addBatch(updateEmployee5);

        int[] response = statement.executeBatch();

        for (int i: response){
            if(i>0)
                continue;
            else
                connection.rollback();      //skip all steps and won not update in DB
        }
        connection.commit();        // if all are true then execute and update in DB

        connection.close();
    }





}