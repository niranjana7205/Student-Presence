package com.geofence.attendance.controller;

import com.geofence.attendance.dto.AttendanceRequest;
import com.geofence.attendance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/mark")
    public ResponseEntity<String> markAttendance(@RequestBody AttendanceRequest request) {
        Long studentId = request.getStudentId();
// Convert String to Long

        String status = attendanceService.markAttendance(
                studentId,
                request.getLatitude(),
                request.getLongitude()
        );

        return ResponseEntity.ok("Attendance marked as: " + status);
    }
}
