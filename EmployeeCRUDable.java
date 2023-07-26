package psut.hr.crud;

import java.util.ArrayList;

import psut.hr.Employee;

public interface EmployeeCRUDable
{
    // Create
    void createEmployee (Employee emp);
    
    // Retrieve
    ArrayList<Employee> getEmployeesList ();
    Employee getEmployeeByID (String id);
    
    // Update
    void updateEmployee (Employee emp);
    
    // Delete
    void deleteEmployee (Employee emp);
}
