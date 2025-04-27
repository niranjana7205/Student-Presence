
package com.geofence.attendance.service;

import com.geofence.attendance.model.Attendance;
import com.geofence.attendance.model.Student;
import com.geofence.attendance.repository.AttendanceRepository;
import com.geofence.attendance.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AttendanceService {

    private static final double CAMPUS_LAT = 12.9716;
    private static final double CAMPUS_LON = 77.5946;
    private static final double ALLOWED_RADIUS_METERS = 100.0;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private AttendanceRepository attendanceRepo;

    public String markAttendance(Long studentId, double lat, double lon) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        double distance = calculateDistance(lat, lon, CAMPUS_LAT, CAMPUS_LON);
        String status = distance <= ALLOWED_RADIUS_METERS ? "PRESENT" : "REJECTED";

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setTimestamp(LocalDateTime.now());
        attendance.setLatitude(lat);
        attendance.setLongitude(lon);
        attendance.setStatus(status);

        attendanceRepo.save(attendance);

        return status;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c * 1000;
    }
}
