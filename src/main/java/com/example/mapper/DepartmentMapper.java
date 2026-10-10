package com.example.mapper;

import com.example.dto.DepartmentDTO;
import com.example.entity.Department;

public class DepartmentMapper {

    public static DepartmentDTO toDTO(Department department) {

        if (department == null) {
            return null;
        }

        return new DepartmentDTO(
                department.getDepartmentId(),
                department.getDepartmentName(),
                department.getDescription()
        );
    }

    public static Department toEntity(DepartmentDTO dto) {

        if (dto == null) {
            return null;
        }

        Department department = new Department();

        department.setDepartmentId(dto.getDepartmentId());
        department.setDepartmentName(dto.getDepartmentName());
        department.setDescription(dto.getDescription());

        return department;
    }
}