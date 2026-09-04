package com.cit.student_api_v2.course.mapper;


import com.cit.student_api_v2.course.dto.CourseRequest;
import com.cit.student_api_v2.course.dto.CourseResponse;
import com.cit.student_api_v2.course.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public CourseResponse toResponse(Course course){
        return new CourseResponse(course.getId(),course.getName());
    }

    public Course requestToEntity (CourseRequest courseRequest){
        return new Course(courseRequest.name());
    }
}
