package com.cit.student_api_v2.enrollment.dto;

import java.time.LocalDateTime;


public record EnrollmentResponse(
        Long Id,
        Long studentId,
        LocalDateTime createdAt
) {}
