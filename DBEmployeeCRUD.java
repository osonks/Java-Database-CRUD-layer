package psut.hr.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import psut.hr.Employee;

public class DBEmployeeCRUD implements EmployeeCRUDable
{
    private ArrayList<Employee> empList;

    private final String DATABASE_URL;
    
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;
    
    private String sqlStatement;
    
    public DBEmployeeCRUD ()
    {
	empList= new ArrayList<> ();
	
	DATABASE_URL = "jdbc:mysql://localhost/employees?autoReconnect=true&useSSL=false";
    }
    
    public void createEmployee (Employee emp)
    {
	sqlStatement= "insert into employees values (?, ?, ?, ?, ?, ?)";
	
	try
	{
            connection= DriverManager.getConnection (DATABASE_URL, "root", "");
            statement= connection.prepareStatement (sqlStatement);
           
            statement.setString (1, emp.getEmpID ());
            statement.setString (2, emp.getEmpDOB ());
            statement.setString (3, emp.getEmpFirstName ());
            statement.setString (4, emp.getEmpLastName ());
            statement.setString (5, String.valueOf (emp.getEmpGender ()));
            statement.setString (6, emp.getEmpHireDate ());
            
            statement.executeUpdate ();
	}
	
	catch (SQLException e)
	{
	    e.printStackTrace ();
	}
	
	finally
	{
	    try
	    {
		    resultSet.close ();
		    statement.close ();
		    connection.close ();
	    }
	
	    catch (SQLException E)
	    {
		    E.printStackTrace ();
	    }
	}
    }

    public ArrayList<Employee> getEmployeesList ()
    {
	sqlStatement= "select * from employees";
	
	try
	{
            connection= DriverManager.getConnection (DATABASE_URL, "root", "");
            statement= connection.prepareStatement (sqlStatement);
            resultSet= statement.executeQuery ();
            Employee tempEmp;
            
            while (resultSet.next ())
            {
        	tempEmp= new Employee (	resultSet.getObject (1).toString (),
        				resultSet.getObject (2).toString (),
        				resultSet.getObject (3).toString (),
        				resultSet.getObject (4).toString (),
        				resultSet.getObject (5).toString ().charAt (0),
        				resultSet.getObject (6).toString ());
        	
        	empList.add (tempEmp);
            }
	}
	
	catch (SQLException e)
	{
	    e.printStackTrace ();
	}
	
	finally
	{
	    try
	    {
		    resultSet.close ();
		    statement.close ();
		    connection.close ();
	    }
	
	    catch (SQLException E)
	    {
		    E.printStackTrace ();
	    }
	}
	
        return empList;
    }

    public Employee getEmployeeByID (String id)
    {
        Employee tempEmp= null;
	sqlStatement= "select * from employees where emp_no = ?";
	
	try
	{
            connection= DriverManager.getConnection (DATABASE_URL, "root", "");
            statement= connection.prepareStatement (sqlStatement);
            statement.setString (1, id);
            resultSet= statement.executeQuery ();
            
            while (resultSet.next ())
            {
        	tempEmp= new Employee (	resultSet.getObject (1).toString (),
			resultSet.getObject (2).toString (),
			resultSet.getObject (3).toString (),
			resultSet.getObject (4).toString (),
			resultSet.getObject (5).toString ().charAt (0),
			resultSet.getObject (6).toString ());
            }
	}
	
	catch (SQLException e)
	{
	    e.printStackTrace ();
	}
	
	finally
	{
	    try
	    {
		    resultSet.close ();
		    statement.close ();
		    connection.close ();
	    }
	
	    catch (SQLException E)
	    {
		    E.printStackTrace ();
	    }
	}
	
        return tempEmp;
    }

    public void updateEmployee (Employee emp)
    {
	System.out.println ("DB: Update logic goes here!");
    }

    public void deleteEmployee (Employee emp)
    {
	System.out.println ("DB: Delete logic goes here!");
    }
}
