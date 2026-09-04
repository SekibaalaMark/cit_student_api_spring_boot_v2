package com.cit.student_api_v2.course.controller;

import com.cit.student_api_v2.api.response.ApiResponse;
import com.cit.student_api_v2.course.dto.CourseRequest;
import com.cit.student_api_v2.course.dto.CourseResponse;
import com.cit.student_api_v2.course.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponse>> addCourse(
            @Valid @RequestBody CourseRequest courseRequest){
        ApiResponse<CourseResponse> apiResponse = courseService.addCourse(courseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
