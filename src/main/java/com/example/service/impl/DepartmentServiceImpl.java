package com.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.DepartmentDTO;
import com.example.entity.Department;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.DepartmentMapper;
import com.example.repository.DepartmentRepository;
import com.example.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(
            DepartmentRepository departmentRepository) {

        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {

        Department department =
                DepartmentMapper.toEntity(departmentDTO);

        Department savedDepartment =
                departmentRepository.save(department);

        return DepartmentMapper.toDTO(savedDepartment);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long departmentId) {

        Department department = departmentRepository
                .findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with ID: "
                                + departmentId));

        return DepartmentMapper.toDTO(department);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {

        return departmentRepository.findAll()
                .stream()
                .map(DepartmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO updateDepartment(
            Long departmentId,
            DepartmentDTO departmentDTO) {

        Department department = departmentRepository
                .findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with ID: "
                                + departmentId));

        department.setDepartmentName(
                departmentDTO.getDepartmentName());

        department.setDescription(
                departmentDTO.getDescription());

        Department updatedDepartment =
                departmentRepository.save(department);

        return DepartmentMapper.toDTO(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long departmentId) {

        Department department = departmentRepository
                .findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with ID: "
                                + departmentId));

        departmentRepository.delete(department);
    }
}