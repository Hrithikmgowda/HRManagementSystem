package com.example.mapper;

import com.example.dto.UserDTO;
import com.example.entity.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {

        if (user == null) {
            return null;
        }

        Long roleId = null;
        String roleName = null;

        if (user.getRole() != null) {
            roleId = user.getRole().getRoleId();
            roleName = user.getRole().getRoleName();
        }

        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.isActive(),
                roleId,
                roleName
        );
    }

    public static User toEntity(UserDTO dto) {

        if (dto == null) {
            return null;
        }

        User user = new User();

        user.setUserId(dto.getUserId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setActive(dto.isActive());

        return user;
    }
}