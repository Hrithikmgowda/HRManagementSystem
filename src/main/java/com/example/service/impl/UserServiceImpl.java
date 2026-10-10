package com.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.UserDTO;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.UserMapper;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        Role role = roleRepository
                .findById(userDTO.getRoleId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found with ID: "
                                + userDTO.getRoleId()));

        User user = UserMapper.toEntity(userDTO);

        user.setRole(role);

        User savedUser =
                userRepository.save(user);

        return UserMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(Long userId) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with ID: "
                                + userId));

        return UserMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(
            Long userId,
            UserDTO userDTO) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with ID: "
                                + userId));

        user.setUsername(
                userDTO.getUsername());

        user.setEmail(
                userDTO.getEmail());

        user.setActive(
                userDTO.isActive());

        if (userDTO.getRoleId() != null) {

            Role role = roleRepository
                    .findById(userDTO.getRoleId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Role not found with ID: "
                                    + userDTO.getRoleId()));

            user.setRole(role);
        }

        User updatedUser =
                userRepository.save(user);

        return UserMapper.toDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with ID: "
                                + userId));

        userRepository.delete(user);
    }
}

