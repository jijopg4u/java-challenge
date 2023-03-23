package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Declare this class as a service and give it the name "EmployeeServiceImpl"
@Service
public class EmployeeServiceImpl implements EmployeeService {
    // Autowire the EmployeeRepository so we can use its methods
    @Autowired
    private EmployeeRepository employeeRepository;

    // Setter method for the EmployeeRepository, used for testing purposes
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Retrieves all employees from the database
    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    // Retrieves a specific employee by ID
    public Employee getEmployee(Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        return optionalEmployee.get();
    }

    // Saves a new employee to the database or updates an existing employee
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    // Deletes an employee from the database by ID
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    // Updates an existing employee in the database
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}