package com.example.service;

import com.example.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeById(Long employeeId);

    List<EmployeeDTO> getAllEmployees();

    List<EmployeeDTO> getEmployeesWithPagination(
            int page,
            int size,
            String sortBy,
            String sortDirection
    );

    // JPQL Query
    List<EmployeeDTO> getEmployeesByStatus(String status);

    // Native SQL Query
    List<EmployeeDTO> getEmployeesWithSalaryGreaterThan(
            double salary
    );

    EmployeeDTO updateEmployee(
            Long employeeId,
            EmployeeDTO employeeDTO
    );

    void deleteEmployee(Long employeeId);
}

