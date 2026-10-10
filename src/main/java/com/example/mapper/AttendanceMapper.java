package com.example.mapper;

import com.example.dto.AttendanceDTO;
import com.example.entity.Attendance;

public class AttendanceMapper {

    public static AttendanceDTO toDTO(Attendance attendance) {

        if (attendance == null) {
            return null;
        }

        Long employeeId = null;
        String employeeName = null;

        if (attendance.getEmployee() != null) {
            employeeId = attendance.getEmployee().getEmployeeId();
            employeeName = attendance.getEmployee().getFirstName()
                    + " " + attendance.getEmployee().getLastName();
        }

        return new AttendanceDTO(
                attendance.getAttendanceId(),
                attendance.getAttendanceDate(),
                attendance.getCheckInTime(),
                attendance.getCheckOutTime(),
                attendance.getStatus(),
                attendance.getWorkingHours(),
                employeeId,
                employeeName
        );
    }

    public static Attendance toEntity(AttendanceDTO dto) {

        if (dto == null) {
            return null;
        }

        Attendance attendance = new Attendance();

        attendance.setAttendanceId(dto.getAttendanceId());
        attendance.setAttendanceDate(dto.getAttendanceDate());
        attendance.setCheckInTime(dto.getCheckInTime());
        attendance.setCheckOutTime(dto.getCheckOutTime());
        attendance.setStatus(dto.getStatus());
        attendance.setWorkingHours(dto.getWorkingHours());

        return attendance;
    }
}