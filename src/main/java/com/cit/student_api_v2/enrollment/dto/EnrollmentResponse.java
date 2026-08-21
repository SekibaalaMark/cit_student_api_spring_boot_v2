package com.cit.student_api_v2.enrollment.dto;

import java.time.LocalDateTime;


public record EnrollmentResponse(
        Long enrollmentId,
        Long studentId,
        String studentRegistrationNumber,
        String studentName,
        LocalDateTime createdAt
) {}
