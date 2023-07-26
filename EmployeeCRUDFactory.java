package psut.hr.crud;

public class EmployeeCRUDFactory
{
    public static EmployeeCRUDable createEmployeeCRUD ()
    {
	//return new FileEmployeeCRUD ();
	return new DBEmployeeCRUD ();
    }
}
