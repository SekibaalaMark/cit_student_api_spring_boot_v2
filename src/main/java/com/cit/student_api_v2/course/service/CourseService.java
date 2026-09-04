package com.cit.student_api_v2.course.service;


import com.cit.student_api_v2.api.response.ApiResponse;
import com.cit.student_api_v2.course.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    //public ApiResponse<>
}
