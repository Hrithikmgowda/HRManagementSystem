package com.example.controller;

import com.example.dto.RoleDTO;
import com.example.service.RoleService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(
            @Valid @RequestBody RoleDTO roleDTO) {

        RoleDTO savedRole =
                roleService.createRole(roleDTO);

        return new ResponseEntity<>(
                savedRole,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleDTO> getRoleById(
            @PathVariable Long roleId) {

        RoleDTO role =
                roleService.getRoleById(roleId);

        return ResponseEntity.ok(role);
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {

        List<RoleDTO> roles =
                roleService.getAllRoles();

        return ResponseEntity.ok(roles);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<RoleDTO> updateRole(
            @PathVariable Long roleId,
            @Valid @RequestBody RoleDTO roleDTO) {

        RoleDTO updatedRole =
                roleService.updateRole(
                        roleId,
                        roleDTO
                );

        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRole(
            @PathVariable Long roleId) {

        roleService.deleteRole(roleId);

        return ResponseEntity.noContent().build();
    }
}