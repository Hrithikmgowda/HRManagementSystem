package com.example.mapper;

import com.example.dto.EmployeeDTO;
import com.example.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDTO toDTO(Employee employee) {

        if (employee == null) {
            return null;
        }

        Long departmentId = null;
        String departmentName = null;

        if (employee.getDepartment() != null) {

            departmentId =
                    employee.getDepartment().getDepartmentId();

            departmentName =
                    employee.getDepartment().getDepartmentName();
        }

        return new EmployeeDTO(
                employee.getEmployeeId(),
                employee.getCreatedAt(),
                employee.getUpdatedAt(),
                employee.getCreatedBy(),
                employee.getUpdatedBy(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getJobTitle(),
                employee.getSalary(),
                employee.getJoiningDate(),
                employee.getStatus(),
                departmentId,
                departmentName
        );
    }

    public static Employee toEntity(EmployeeDTO dto) {

        if (dto == null) {
            return null;
        }

        Employee employee = new Employee();

        employee.setEmployeeId(
                dto.getEmployeeId());

        employee.setFirstName(
                dto.getFirstName());

        employee.setLastName(
                dto.getLastName());

        employee.setEmail(
                dto.getEmail());

        employee.setPhone(
                dto.getPhone());

        employee.setJobTitle(
                dto.getJobTitle());

        employee.setSalary(
                dto.getSalary());

        employee.setJoiningDate(
                dto.getJoiningDate());

        employee.setStatus(
                dto.getStatus());

        return employee;
    }
}

