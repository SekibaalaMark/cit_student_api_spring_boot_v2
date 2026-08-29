package com.cit.student_api_v2.enrollment.controller;

import com.cit.student_api_v2.api.response.ApiResponse;
import com.cit.student_api_v2.enrollment.dto.EnrollmentRequest;
import com.cit.student_api_v2.enrollment.dto.EnrollmentResponse;
import com.cit.student_api_v2.enrollment.service.EnrollmentService;
import com.cit.student_api_v2.page.response.PageResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<EnrollmentResponse>>> getAllEnrollments(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        PageResponse<EnrollmentResponse> pageResponse = enrollmentService.getAllEnrollments(page,pageSize);
        ApiResponse<PageResponse<EnrollmentResponse>> apiResponse = new ApiResponse<>(
                "SUCCESS",
                "Page Of Enrollments",
                pageResponse
                );
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

    @GetMapping("summary/id/{id}")
    public ResponseEntity<ApiResponse<EnrollmentResponse>> getEnrollmentSummaryById(@PathVariable Long id){
        ApiResponse<EnrollmentResponse> apiResponse = enrollmentService.getEnrollmentSummaryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EnrollmentResponse>> updateEnrollment(
            @PathVariable Long id,
            @Valid @RequestBody EnrollmentRequest enrollmentRequest
    ){
        ApiResponse<EnrollmentResponse> apiResponse = enrollmentService.updateEnrollment(id,enrollmentRequest);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }





}
