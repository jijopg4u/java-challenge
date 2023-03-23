package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This interface extends the JpaRepository interface, which provides basic CRUD functionality for our Employee entity
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
