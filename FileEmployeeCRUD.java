package psut.hr.crud;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import psut.hr.Employee;

public class FileEmployeeCRUD implements EmployeeCRUDable
{
    private BufferedReader input;
    private BufferedWriter output;
    private ArrayList<Employee> empList;
    
    public FileEmployeeCRUD ()
    {
	Employee tempEmp;
	empList= new ArrayList<> ();

        try
        {
            input= new BufferedReader (new InputStreamReader (new FileInputStream ( new File ("employees.csv"))));

            String s;
            String[] sSplit;

            while (input.ready ())
            {
        	s= input.readLine ();
        	sSplit= s.split ("[,\n]");
        	tempEmp= new Employee (sSplit[0], sSplit[1], sSplit[2], sSplit[3], sSplit[4].charAt (0), sSplit[5]);
        	empList.add (tempEmp);
            }

            input.close ();
            
            output= new BufferedWriter (new OutputStreamWriter (new FileOutputStream ("employees.csv", true)));
        }
        
        catch (IOException e)
        {
            System.out.println ("File I/O error");
        }
    }

    public void createEmployee (Employee emp)
    {
	String tempEmp;
	
	tempEmp= emp.getEmpID () + "," + emp.getEmpDOB () + "," + emp.getEmpFirstName () + "," + emp.getEmpLastName () + "," + emp.getEmpGender () + "," + emp.getEmpHireDate () + "\n";
	
	try
	{
	    output.write (tempEmp);
	    output.close ();
	}
	
	catch (IOException e)
        {
            System.out.println ("File input error");
        }
    }

    public ArrayList<Employee> getEmployeesList ()
    {
	return empList;
    }

    public Employee getEmployeeByID (String id)
    {
	Employee tempEmp= null;
	
	for (Employee e : empList)
	    if (e.getEmpID ().equals (id))
		tempEmp = e;
	
	return tempEmp;
    }

    public void updateEmployee (Employee emp)
    {
	System.out.println ("File: Update logic goes here!");
    }

    public void deleteEmployee (Employee emp)
    {
	System.out.println ("File: Delete logic goes here!");
    }
}
