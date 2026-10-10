package com.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.AuditLogDTO;
import com.example.entity.AuditLog;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.AuditLogMapper;
import com.example.repository.AuditLogRepository;
import com.example.service.AuditLogService;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogServiceImpl(
            AuditLogRepository auditLogRepository) {

        this.auditLogRepository = auditLogRepository;
    }

    @Override
    public AuditLogDTO createAuditLog(AuditLogDTO auditLogDTO) {

        AuditLog auditLog =
                AuditLogMapper.toEntity(auditLogDTO);

        AuditLog savedAuditLog =
                auditLogRepository.save(auditLog);

        return AuditLogMapper.toDTO(savedAuditLog);
    }

    @Override
    public AuditLogDTO getAuditLogById(Long auditId) {

        AuditLog auditLog =
                auditLogRepository.findById(auditId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Audit log not found with ID: "
                                + auditId));

        return AuditLogMapper.toDTO(auditLog);
    }

    @Override
    public List<AuditLogDTO> getAllAuditLogs() {

        return auditLogRepository.findAll()
                .stream()
                .map(AuditLogMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAuditLog(Long auditId) {

        AuditLog auditLog =
                auditLogRepository.findById(auditId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Audit log not found with ID: "
                                + auditId));

        auditLogRepository.delete(auditLog);
    }
}

