package com.example.mapper;

import com.example.dto.AuditLogDTO;
import com.example.entity.AuditLog;

public class AuditLogMapper {

    public static AuditLogDTO toDTO(AuditLog auditLog) {

        if (auditLog == null) {
            return null;
        }

        return new AuditLogDTO(
                auditLog.getAuditId(),
                auditLog.getAction(),
                auditLog.getEntityName(),
                auditLog.getEntityId(),
                auditLog.getPerformedBy(),
                auditLog.getPerformedAt(),
                auditLog.getDescription()
        );
    }

    public static AuditLog toEntity(AuditLogDTO dto) {

        if (dto == null) {
            return null;
        }

        AuditLog auditLog = new AuditLog();

        auditLog.setAuditId(dto.getAuditId());
        auditLog.setAction(dto.getAction());
        auditLog.setEntityName(dto.getEntityName());
        auditLog.setEntityId(dto.getEntityId());
        auditLog.setPerformedBy(dto.getPerformedBy());
        auditLog.setPerformedAt(dto.getPerformedAt());
        auditLog.setDescription(dto.getDescription());

        return auditLog;
    }
}