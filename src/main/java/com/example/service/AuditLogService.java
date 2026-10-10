package com.example.service;

import com.example.dto.AuditLogDTO;

import java.util.List;

public interface AuditLogService {

    AuditLogDTO createAuditLog(AuditLogDTO auditLogDTO);

    AuditLogDTO getAuditLogById(Long auditId);

    List<AuditLogDTO> getAllAuditLogs();

    void deleteAuditLog(Long auditId);
}