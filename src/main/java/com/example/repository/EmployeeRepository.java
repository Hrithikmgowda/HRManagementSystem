package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // JPQL Query
    @Query("SELECT e FROM Employee e WHERE e.status = :status")
    List<Employee> findEmployeesByStatus(
            @Param("status") String status
    );

 // Native SQL Query
 @Query(
     value = "SELECT * FROM employees WHERE salary > :salary",
     nativeQuery = true
 )
 List<Employee> findEmployeesWithSalaryGreaterThan(
         @Param("salary") double salary
 );


}

