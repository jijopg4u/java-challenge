package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Cacheable(value = "employees")
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        return employees;
    }

    @Cacheable(value = "employees", key = "#employeeId")
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        if (employee == null) {
            System.out.println("Employee not found for the id: " + employeeId);
        }
        return employee;
    }

    @CacheEvict(value = "employees", allEntries = true)
    @PostMapping("/employees")
    public void saveEmployee(Employee employee) {
        employeeService.saveEmployee(employee);
        System.out.println("Employee saved successfully");
    }

    @CacheEvict(value = "employees", key = "#employeeId")
    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        if (employee == null) {
            System.out.println("Employee not found for id: " + employeeId);
        } else {
            employeeService.deleteEmployee(employeeId);
            System.out.println("Employee deleted successfully");
        }
    }

    @CacheEvict(value = "employees", key = "#employeeId")
    @PutMapping("/employees/{employeeId}")
    public void updateEmployee(@RequestBody Employee employee,
            @PathVariable(name = "employeeId") Long employeeId) {
        Employee emp = employeeService.getEmployee(employeeId);
        if (emp == null) {
            System.out.println("Employee not found for id: " + employeeId);
        } else {
            employee.setId(employeeId);
            employeeService.updateEmployee(employee);
        }
    }
}