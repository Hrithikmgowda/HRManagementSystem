package com.example.controller;

import com.example.dto.DepartmentDTO;
import com.example.service.DepartmentService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(
            @Valid @RequestBody DepartmentDTO departmentDTO) {

        DepartmentDTO savedDepartment =
                departmentService.createDepartment(departmentDTO);

        return new ResponseEntity<>(
                savedDepartment,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(
            @PathVariable Long departmentId) {

        DepartmentDTO department =
                departmentService.getDepartmentById(departmentId);

        return ResponseEntity.ok(department);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {

        List<DepartmentDTO> departments =
                departmentService.getAllDepartments();

        return ResponseEntity.ok(departments);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartment(
            @PathVariable Long departmentId,
            @Valid @RequestBody DepartmentDTO departmentDTO) {

        DepartmentDTO updatedDepartment =
                departmentService.updateDepartment(
                        departmentId,
                        departmentDTO
                );

        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> deleteDepartment(
            @PathVariable Long departmentId) {

        departmentService.deleteDepartment(departmentId);

        return ResponseEntity.noContent().build();
    }
}