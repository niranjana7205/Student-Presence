package com.geofence.attendance.dto;

public class AttendanceRequest {
    private Long studentId;
    private double latitude;
    private double longitude;

    // Getters
    public Long getStudentId() {
        return studentId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    // Setters (needed for @RequestBody to work)
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
