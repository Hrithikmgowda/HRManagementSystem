package com.example.controller;

import com.example.dto.AuditLogDTO;
import com.example.service.AuditLogService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
public class AuditLogController {

    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @PostMapping
    public ResponseEntity<AuditLogDTO> createAuditLog(
            @RequestBody AuditLogDTO auditLogDTO) {

        AuditLogDTO savedAuditLog =
                auditLogService.createAuditLog(auditLogDTO);

        return new ResponseEntity<>(
                savedAuditLog,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{auditId}")
    public ResponseEntity<AuditLogDTO> getAuditLogById(
            @PathVariable Long auditId) {

        AuditLogDTO auditLog =
                auditLogService.getAuditLogById(auditId);

        return ResponseEntity.ok(auditLog);
    }

    @GetMapping
    public ResponseEntity<List<AuditLogDTO>> getAllAuditLogs() {

        List<AuditLogDTO> auditLogs =
                auditLogService.getAllAuditLogs();

        return ResponseEntity.ok(auditLogs);
    }

    @DeleteMapping("/{auditId}")
    public ResponseEntity<Void> deleteAuditLog(
            @PathVariable Long auditId) {

        auditLogService.deleteAuditLog(auditId);

        return ResponseEntity.noContent().build();
    }
}