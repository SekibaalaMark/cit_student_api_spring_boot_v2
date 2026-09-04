package com.cit.student_api_v2.course.service;


import com.cit.student_api_v2.api.response.ApiResponse;
import com.cit.student_api_v2.course.dto.CourseRequest;
import com.cit.student_api_v2.course.dto.CourseResponse;
import com.cit.student_api_v2.course.mapper.CourseMapper;
import com.cit.student_api_v2.course.model.Course;
import com.cit.student_api_v2.course.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }


    public ApiResponse<CourseResponse> addCourse(CourseRequest courseRequest){
        Course course = courseMapper.requestToEntity(courseRequest);
        CourseResponse courseResponse = courseMapper.toResponse(courseRepository.save(course));
        return new ApiResponse<>("SUCCESS","Course Added Successfully",courseResponse);
    }
}
