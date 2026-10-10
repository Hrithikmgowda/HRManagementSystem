package com.example.service;

import com.example.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {

    DepartmentDTO createDepartment(DepartmentDTO departmentDTO);

    DepartmentDTO getDepartmentById(Long departmentId);

    List<DepartmentDTO> getAllDepartments();

    DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO);

    void deleteDepartment(Long departmentId);
}