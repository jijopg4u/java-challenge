package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import java.util.List;

/**
 * This interface defines methods for interacting with employee data.
 */
public interface EmployeeService {
    /**
     * Retrieves all employees from the database.
     * @return a list of all employees
     */
    List<Employee> retrieveEmployees();

    /**
     * Retrieves an employee by ID.
     * @param employeeId
     * @return the employee with the given ID, or null if no such employee exists
     */
    Employee getEmployee(Long employeeId);

    /**
     * Saves an employee to the database.
     * @param employee 
     */
    void saveEmployee(Employee employee);

    /**
     * Deletes an employee from the database by their ID.
     * @param employeeId 
     */
    void deleteEmployee(Long employeeId);

    /**
     * Updates an employee in the database.
     * @param employee
     */
    void updateEmployee(Employee employee);
}
