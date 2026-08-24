package com.cit.student_api_v2.enrollment.controller;

import com.cit.student_api_v2.api.response.ApiResponse;
import com.cit.student_api_v2.enrollment.dto.EnrollmentRequest;
import com.cit.student_api_v2.enrollment.dto.EnrollmentResponse;
import com.cit.student_api_v2.enrollment.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EnrollmentResponse>> addEnrollment(@RequestBody EnrollmentRequest enrollmentRequest){
        ApiResponse<EnrollmentResponse> apiResponse = enrollmentService.addEnrollment(enrollmentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }




}
