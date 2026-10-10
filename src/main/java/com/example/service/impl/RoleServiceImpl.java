package com.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.RoleDTO;
import com.example.entity.Role;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.RoleMapper;
import com.example.repository.RoleRepository;
import com.example.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {

        Role role = RoleMapper.toEntity(roleDTO);

        Role savedRole = roleRepository.save(role);

        return RoleMapper.toDTO(savedRole);
    }

    @Override
    public RoleDTO getRoleById(Long roleId) {

        Role role = roleRepository
                .findById(roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found with ID: " + roleId));

        return RoleMapper.toDTO(role);
    }

    @Override
    public List<RoleDTO> getAllRoles() {

        return roleRepository.findAll()
                .stream()
                .map(RoleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO updateRole(
            Long roleId,
            RoleDTO roleDTO) {

        Role role = roleRepository
                .findById(roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found with ID: " + roleId));

        role.setRoleName(roleDTO.getRoleName());
        role.setDescription(roleDTO.getDescription());

        Role updatedRole = roleRepository.save(role);

        return RoleMapper.toDTO(updatedRole);
    }

    @Override
    public void deleteRole(Long roleId) {

        Role role = roleRepository
                .findById(roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found with ID: " + roleId));

        roleRepository.delete(role);
    }
}

