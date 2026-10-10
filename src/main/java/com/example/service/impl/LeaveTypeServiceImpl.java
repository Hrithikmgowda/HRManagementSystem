package com.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.LeaveTypeDTO;
import com.example.entity.LeaveType;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.LeaveTypeMapper;
import com.example.repository.LeaveTypeRepository;
import com.example.service.LeaveTypeService;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

    private final LeaveTypeRepository leaveTypeRepository;

    public LeaveTypeServiceImpl(
            LeaveTypeRepository leaveTypeRepository) {

        this.leaveTypeRepository = leaveTypeRepository;
    }

    @Override
    public LeaveTypeDTO createLeaveType(LeaveTypeDTO leaveTypeDTO) {

        LeaveType leaveType =
                LeaveTypeMapper.toEntity(leaveTypeDTO);

        LeaveType savedLeaveType =
                leaveTypeRepository.save(leaveType);

        return LeaveTypeMapper.toDTO(savedLeaveType);
    }

    @Override
    public LeaveTypeDTO getLeaveTypeById(Long leaveTypeId) {

        LeaveType leaveType = leaveTypeRepository
                .findById(leaveTypeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leave type not found with ID: "
                                + leaveTypeId));

        return LeaveTypeMapper.toDTO(leaveType);
    }

    @Override
    public List<LeaveTypeDTO> getAllLeaveTypes() {

        return leaveTypeRepository.findAll()
                .stream()
                .map(LeaveTypeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LeaveTypeDTO updateLeaveType(
            Long leaveTypeId,
            LeaveTypeDTO leaveTypeDTO) {

        LeaveType leaveType = leaveTypeRepository
                .findById(leaveTypeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leave type not found with ID: "
                                + leaveTypeId));

        leaveType.setLeaveTypeName(
                leaveTypeDTO.getLeaveTypeName());

        leaveType.setDescription(
                leaveTypeDTO.getDescription());

        leaveType.setMaximumDays(
                leaveTypeDTO.getMaximumDays());

        LeaveType updatedLeaveType =
                leaveTypeRepository.save(leaveType);

        return LeaveTypeMapper.toDTO(updatedLeaveType);
    }

    @Override
    public void deleteLeaveType(Long leaveTypeId) {

        LeaveType leaveType = leaveTypeRepository
                .findById(leaveTypeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leave type not found with ID: "
                                + leaveTypeId));

        leaveTypeRepository.delete(leaveType);
    }
}

