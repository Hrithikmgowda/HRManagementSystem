package com.example.controller;

import com.example.dto.AttendanceDTO;
import com.example.service.AttendanceService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public ResponseEntity<AttendanceDTO> createAttendance(
            @Valid @RequestBody AttendanceDTO attendanceDTO) {

        AttendanceDTO savedAttendance =
                attendanceService.createAttendance(attendanceDTO);

        return new ResponseEntity<>(
                savedAttendance,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{attendanceId}")
    public ResponseEntity<AttendanceDTO> getAttendanceById(
            @PathVariable Long attendanceId) {

        AttendanceDTO attendance =
                attendanceService.getAttendanceById(attendanceId);

        return ResponseEntity.ok(attendance);
    }

    @GetMapping
    public ResponseEntity<List<AttendanceDTO>> getAllAttendance() {

        List<AttendanceDTO> attendanceList =
                attendanceService.getAllAttendance();

        return ResponseEntity.ok(attendanceList);
    }

    @PutMapping("/{attendanceId}")
    public ResponseEntity<AttendanceDTO> updateAttendance(
            @PathVariable Long attendanceId,
            @Valid @RequestBody AttendanceDTO attendanceDTO) {

        AttendanceDTO updatedAttendance =
                attendanceService.updateAttendance(
                        attendanceId,
                        attendanceDTO
                );

        return ResponseEntity.ok(updatedAttendance);
    }

    @DeleteMapping("/{attendanceId}")
    public ResponseEntity<Void> deleteAttendance(
            @PathVariable Long attendanceId) {

        attendanceService.deleteAttendance(attendanceId);

        return ResponseEntity.noContent().build();
    }
}