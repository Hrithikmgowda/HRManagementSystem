package com.example.dto;

import jakarta.validation.constraints.NotBlank;

public class DepartmentDTO {

    private Long departmentId;

    @NotBlank(message = "Department name is required")
    private String departmentName;

    private String description;

    public DepartmentDTO() {
    }

    public DepartmentDTO(Long departmentId, String departmentName,
                          String description) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.description = description;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}