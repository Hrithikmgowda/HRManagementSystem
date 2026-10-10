package com.example.service;

import com.example.dto.AttendanceDTO;

import java.util.List;

public interface AttendanceService {

    AttendanceDTO createAttendance(AttendanceDTO attendanceDTO);

    AttendanceDTO getAttendanceById(Long attendanceId);

    List<AttendanceDTO> getAllAttendance();

    AttendanceDTO updateAttendance(Long attendanceId, AttendanceDTO attendanceDTO);

    void deleteAttendance(Long attendanceId);
}