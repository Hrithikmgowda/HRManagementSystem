package com.example.service.impl;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.AttendanceDTO;
import com.example.entity.Attendance;
import com.example.entity.Employee;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.AttendanceMapper;
import com.example.repository.AttendanceRepository;
import com.example.repository.EmployeeRepository;
import com.example.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public AttendanceServiceImpl(
            AttendanceRepository attendanceRepository,
            EmployeeRepository employeeRepository) {

        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public AttendanceDTO createAttendance(AttendanceDTO attendanceDTO) {

        Employee employee = employeeRepository
                .findById(attendanceDTO.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with ID: "
                                + attendanceDTO.getEmployeeId()));

        Attendance attendance =
                AttendanceMapper.toEntity(attendanceDTO);

        attendance.setEmployee(employee);

        calculateWorkingHours(attendance);

        Attendance savedAttendance =
                attendanceRepository.save(attendance);

        return AttendanceMapper.toDTO(savedAttendance);
    }

    @Override
    public AttendanceDTO getAttendanceById(Long attendanceId) {

        Attendance attendance = attendanceRepository
                .findById(attendanceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Attendance not found with ID: "
                                + attendanceId));

        return AttendanceMapper.toDTO(attendance);
    }

    @Override
    public List<AttendanceDTO> getAllAttendance() {

        return attendanceRepository.findAll()
                .stream()
                .map(AttendanceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceDTO updateAttendance(
            Long attendanceId,
            AttendanceDTO attendanceDTO) {

        Attendance attendance = attendanceRepository
                .findById(attendanceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Attendance not found with ID: "
                                + attendanceId));

        attendance.setAttendanceDate(
                attendanceDTO.getAttendanceDate());

        attendance.setCheckInTime(
                attendanceDTO.getCheckInTime());

        attendance.setCheckOutTime(
                attendanceDTO.getCheckOutTime());

        attendance.setStatus(
                attendanceDTO.getStatus());

        if (attendanceDTO.getEmployeeId() != null) {

            Employee employee = employeeRepository
                    .findById(attendanceDTO.getEmployeeId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Employee not found with ID: "
                                    + attendanceDTO.getEmployeeId()));

            attendance.setEmployee(employee);
        }

        calculateWorkingHours(attendance);

        Attendance updatedAttendance =
                attendanceRepository.save(attendance);

        return AttendanceMapper.toDTO(updatedAttendance);
    }

    @Override
    public void deleteAttendance(Long attendanceId) {

        Attendance attendance = attendanceRepository
                .findById(attendanceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Attendance not found with ID: "
                                + attendanceId));

        attendanceRepository.delete(attendance);
    }

    private void calculateWorkingHours(Attendance attendance) {

        if (attendance.getCheckInTime() != null
                && attendance.getCheckOutTime() != null) {

            long minutes = Duration.between(
                    attendance.getCheckInTime(),
                    attendance.getCheckOutTime()
            ).toMinutes();

            attendance.setWorkingHours(minutes / 60.0);
        }
    }
}