package com.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.LeaveBalanceDTO;
import com.example.entity.Employee;
import com.example.entity.LeaveBalance;
import com.example.entity.LeaveType;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.LeaveBalanceMapper;
import com.example.repository.EmployeeRepository;
import com.example.repository.LeaveBalanceRepository;
import com.example.repository.LeaveTypeRepository;
import com.example.service.LeaveBalanceService;

@Service
public class LeaveBalanceServiceImpl implements LeaveBalanceService {

    private final LeaveBalanceRepository leaveBalanceRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveTypeRepository leaveTypeRepository;

    public LeaveBalanceServiceImpl(
            LeaveBalanceRepository leaveBalanceRepository,
            EmployeeRepository employeeRepository,
            LeaveTypeRepository leaveTypeRepository) {

        this.leaveBalanceRepository = leaveBalanceRepository;
        this.employeeRepository = employeeRepository;
        this.leaveTypeRepository = leaveTypeRepository;
    }

    @Override
    public LeaveBalanceDTO createLeaveBalance(
            LeaveBalanceDTO leaveBalanceDTO) {

        Employee employee = employeeRepository
                .findById(leaveBalanceDTO.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with ID: "
                                + leaveBalanceDTO.getEmployeeId()));

        LeaveType leaveType = leaveTypeRepository
                .findById(leaveBalanceDTO.getLeaveTypeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leave type not found with ID: "
                                + leaveBalanceDTO.getLeaveTypeId()));

        LeaveBalance leaveBalance =
                LeaveBalanceMapper.toEntity(leaveBalanceDTO);

        leaveBalance.setEmployee(employee);
        leaveBalance.setLeaveType(leaveType);

        LeaveBalance savedLeaveBalance =
                leaveBalanceRepository.save(leaveBalance);

        return LeaveBalanceMapper.toDTO(savedLeaveBalance);
    }

    @Override
    public LeaveBalanceDTO getLeaveBalanceById(Long balanceId) {

        LeaveBalance leaveBalance =
                leaveBalanceRepository.findById(balanceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leave balance not found with ID: "
                                + balanceId));

        return LeaveBalanceMapper.toDTO(leaveBalance);
    }

    @Override
    public List<LeaveBalanceDTO> getAllLeaveBalances() {

        return leaveBalanceRepository.findAll()
                .stream()
                .map(LeaveBalanceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LeaveBalanceDTO updateLeaveBalance(
            Long balanceId,
            LeaveBalanceDTO leaveBalanceDTO) {

        LeaveBalance leaveBalance =
                leaveBalanceRepository.findById(balanceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leave balance not found with ID: "
                                + balanceId));

        leaveBalance.setTotalDays(
                leaveBalanceDTO.getTotalDays());

        leaveBalance.setUsedDays(
                leaveBalanceDTO.getUsedDays());

        leaveBalance.setRemainingDays(
                leaveBalanceDTO.getRemainingDays());

        if (leaveBalanceDTO.getEmployeeId() != null) {

            Employee employee = employeeRepository
                    .findById(leaveBalanceDTO.getEmployeeId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Employee not found with ID: "
                                    + leaveBalanceDTO.getEmployeeId()));

            leaveBalance.setEmployee(employee);
        }

        if (leaveBalanceDTO.getLeaveTypeId() != null) {

            LeaveType leaveType = leaveTypeRepository
                    .findById(leaveBalanceDTO.getLeaveTypeId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Leave type not found with ID: "
                                    + leaveBalanceDTO.getLeaveTypeId()));

            leaveBalance.setLeaveType(leaveType);
        }

        LeaveBalance updatedLeaveBalance =
                leaveBalanceRepository.save(leaveBalance);

        return LeaveBalanceMapper.toDTO(updatedLeaveBalance);
    }

    @Override
    public void deleteLeaveBalance(Long balanceId) {

        LeaveBalance leaveBalance =
                leaveBalanceRepository.findById(balanceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leave balance not found with ID: "
                                + balanceId));

        leaveBalanceRepository.delete(leaveBalance);
    }
}

