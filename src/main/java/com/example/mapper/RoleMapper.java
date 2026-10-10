package com.example.mapper;

import com.example.dto.RoleDTO;
import com.example.entity.Role;

public class RoleMapper {

    public static RoleDTO toDTO(Role role) {

        if (role == null) {
            return null;
        }

        return new RoleDTO(
                role.getRoleId(),
                role.getRoleName(),
                role.getDescription()
        );
    }

    public static Role toEntity(RoleDTO dto) {

        if (dto == null) {
            return null;
        }

        Role role = new Role();

        role.setRoleId(dto.getRoleId());
        role.setRoleName(dto.getRoleName());
        role.setDescription(dto.getDescription());

        return role;
    }
}