package com.example.dto;

import java.time.LocalDateTime;

public class AuditLogDTO {

    private Long auditId;
    private String action;
    private String entityName;
    private Long entityId;
    private String performedBy;
    private LocalDateTime performedAt;
    private String description;

    public AuditLogDTO() {
    }

    public AuditLogDTO(Long auditId, String action, String entityName,
                       Long entityId, String performedBy,
                       LocalDateTime performedAt, String description) {
        this.auditId = auditId;
        this.action = action;
        this.entityName = entityName;
        this.entityId = entityId;
        this.performedBy = performedBy;
        this.performedAt = performedAt;
        this.description = description;
    }

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public LocalDateTime getPerformedAt() {
        return performedAt;
    }

    public void setPerformedAt(LocalDateTime performedAt) {
        this.performedAt = performedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
