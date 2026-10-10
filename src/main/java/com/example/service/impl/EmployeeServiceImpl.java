package com.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.dto.EmployeeDTO;
import com.example.entity.Department;
import com.example.entity.Employee;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.EmployeeMapper;
import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {


private static final Logger logger =
        LoggerFactory.getLogger(EmployeeServiceImpl.class);

private final EmployeeRepository employeeRepository;
private final DepartmentRepository departmentRepository;

public EmployeeServiceImpl(
        EmployeeRepository employeeRepository,
        DepartmentRepository departmentRepository) {

    this.employeeRepository = employeeRepository;
    this.departmentRepository = departmentRepository;
}

// Create Employee
@Override
@CacheEvict(value = {"employees", "allEmployees"}, allEntries = true)
public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {

    logger.info("Creating employee with email: {}",
            employeeDTO.getEmail());

    Employee employee = EmployeeMapper.toEntity(employeeDTO);

    if (employeeDTO.getDepartmentId() != null) {
        Department department = departmentRepository
                .findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found with ID: "
                                + employeeDTO.getDepartmentId()));

        employee.setDepartment(department);
    }

    Employee savedEmployee = employeeRepository.save(employee);

    logger.info("Employee created successfully with ID: {}",
            savedEmployee.getEmployeeId());

    return EmployeeMapper.toDTO(savedEmployee);
}

// Get Employee By ID - Cached
@Override
@Cacheable(value = "employees", key = "#p0", unless = "#result == null")
public EmployeeDTO getEmployeeById(Long employeeId) {

    logger.info("Fetching employee with ID: {}", employeeId);

    Employee employee = employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException(
                    "Employee not found with ID: " + employeeId));

    return EmployeeMapper.toDTO(employee);
}

// Get All Employees - Cached
@Override
@Cacheable(value = "allEmployees")
public List<EmployeeDTO> getAllEmployees() {

    logger.info("Fetching all employees");

    return employeeRepository.findAll()
            .stream()
            .map(EmployeeMapper::toDTO)
            .collect(Collectors.toList());
}

// Pagination and Sorting
@Override
public List<EmployeeDTO> getEmployeesWithPagination(
        int page,
        int size,
        String sortBy,
        String sortDirection) {

    logger.info(
            "Fetching employees with pagination - page: {}, size: {}, sortBy: {}, direction: {}",
            page, size, sortBy, sortDirection);

    Sort sort;

    if (sortDirection.equalsIgnoreCase("desc")) {
        sort = Sort.by(sortBy).descending();
    } else {
        sort = Sort.by(sortBy).ascending();
    }

    Pageable pageable = PageRequest.of(page, size, sort);

    Page<Employee> employeePage =
            employeeRepository.findAll(pageable);

    return employeePage.getContent()
            .stream()
            .map(EmployeeMapper::toDTO)
            .collect(Collectors.toList());
}

// JPQL Query
@Override
public List<EmployeeDTO> getEmployeesByStatus(String status) {

    logger.info("Fetching employees with status: {}", status);

    return employeeRepository.findEmployeesByStatus(status)
            .stream()
            .map(EmployeeMapper::toDTO)
            .collect(Collectors.toList());
}

// Native SQL Query
@Override
public List<EmployeeDTO> getEmployeesWithSalaryGreaterThan(
        double salary) {

    logger.info("Fetching employees with salary greater than: {}",
            salary);

    return employeeRepository
            .findEmployeesWithSalaryGreaterThan(salary)
            .stream()
            .map(EmployeeMapper::toDTO)
            .collect(Collectors.toList());
}

// Update Employee
@Override
@CacheEvict(value = {"employees", "allEmployees"}, allEntries = true)
public EmployeeDTO updateEmployee(
        Long employeeId,
        EmployeeDTO employeeDTO) {

    logger.info("Updating employee with ID: {}", employeeId);

    Employee employee = employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException(
                    "Employee not found with ID: " + employeeId));

    employee.setFirstName(employeeDTO.getFirstName());
    employee.setLastName(employeeDTO.getLastName());
    employee.setEmail(employeeDTO.getEmail());
    employee.setPhone(employeeDTO.getPhone());
    employee.setJobTitle(employeeDTO.getJobTitle());
    employee.setSalary(employeeDTO.getSalary());
    employee.setJoiningDate(employeeDTO.getJoiningDate());
    employee.setStatus(employeeDTO.getStatus());

    if (employeeDTO.getDepartmentId() != null) {
        Department department = departmentRepository
                .findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found with ID: "
                                + employeeDTO.getDepartmentId()));

        employee.setDepartment(department);
    }

    Employee updatedEmployee = employeeRepository.save(employee);

    logger.info("Employee updated successfully with ID: {}",
            updatedEmployee.getEmployeeId());

    return EmployeeMapper.toDTO(updatedEmployee);
}

// Delete Employee
@Override
@CacheEvict(value = {"employees", "allEmployees"}, allEntries = true)
public void deleteEmployee(Long employeeId) {

    logger.info("Deleting employee with ID: {}", employeeId);

    Employee employee = employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException(
                    "Employee not found with ID: " + employeeId));

    employeeRepository.delete(employee);

    logger.info("Employee deleted successfully with ID: {}",
            employeeId);
}


}
