package com.example.controller;

import com.example.dto.EmployeeDTO;
import com.example.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Create Employee
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(
            @Valid @RequestBody EmployeeDTO employeeDTO) {

        EmployeeDTO savedEmployee =
                employeeService.createEmployee(employeeDTO);

        return new ResponseEntity<>(
                savedEmployee,
                HttpStatus.CREATED
        );
    }

    // Get Employee by ID
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(
            @PathVariable Long employeeId) {

        EmployeeDTO employee =
                employeeService.getEmployeeById(employeeId);

        return ResponseEntity.ok(employee);
    }

    // Get All Employees
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {

        List<EmployeeDTO> employees =
                employeeService.getAllEmployees();

        return ResponseEntity.ok(employees);
    }

    // Pagination and Sorting
    @GetMapping("/pagination")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "employeeId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        List<EmployeeDTO> employees =
                employeeService.getEmployeesWithPagination(
                        page,
                        size,
                        sortBy,
                        sortDirection
                );

        return ResponseEntity.ok(employees);
    }

    // JPQL Search - Employees by Status
    @GetMapping("/search/status")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByStatus(
            @RequestParam String status) {

        List<EmployeeDTO> employees =
                employeeService.getEmployeesByStatus(status);

        return ResponseEntity.ok(employees);
    }

    // Native SQL Search - Employees by Salary
    @GetMapping("/search/salary")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesWithSalaryGreaterThan(
            @RequestParam double salary) {

        List<EmployeeDTO> employees =
                employeeService.getEmployeesWithSalaryGreaterThan(
                        salary
                );

        return ResponseEntity.ok(employees);
    }

    // Update Employee
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable Long employeeId,
            @Valid @RequestBody EmployeeDTO employeeDTO) {

        EmployeeDTO updatedEmployee =
                employeeService.updateEmployee(
                        employeeId,
                        employeeDTO
                );

        return ResponseEntity.ok(updatedEmployee);
    }

    // Delete Employee
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long employeeId) {

        employeeService.deleteEmployee(employeeId);

        return ResponseEntity.noContent().build();
    }
}

