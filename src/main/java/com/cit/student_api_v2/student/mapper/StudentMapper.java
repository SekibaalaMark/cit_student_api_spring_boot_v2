package com.cit.student_api_v2.student.mapper;


import com.cit.student_api_v2.student.dto.StudentRequest;
import com.cit.student_api_v2.student.dto.StudentResponse;
import com.cit.student_api_v2.student.dto.StudentUpdateRequest;
import com.cit.student_api_v2.student.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequest studentRequest){
        if(studentRequest == null){
            return null;
        }

        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setRegistrationNumber(studentRequest.getRegistrationNumber());
        student.setCgpa(studentRequest.getCgpa());
        student.setStatus(studentRequest.getStatus());
        return student;
    }


    public void updateEntityFromRequest(StudentUpdateRequest studentRequest, Student existingStudent){
        if(studentRequest == null || existingStudent == null){
            return;
        }

        if(studentRequest.getName() != null){
            existingStudent.setName(studentRequest.getName());
        }

        if(studentRequest.getRegistrationNumber() != null){
            existingStudent.setRegistrationNumber(studentRequest.getRegistrationNumber());
        }

        if(studentRequest.getCgpa() != null){
            existingStudent.setCgpa(studentRequest.getCgpa());
        }

        if(studentRequest.getStatus() != null){
            existingStudent.setStatus(studentRequest.getStatus());
        }
    }

    public StudentResponse toResponse(Student student){
        if(student == null){
            return null;
        }
        StudentResponse response = new StudentResponse();
        response.setCgpa(student.getCgpa());
        response.setName(student.getName());
        response.setRegistrationNumber(student.getRegistrationNumber());
        response.setId(student.getId());
        response.setStatus(student.getStatus());

        return response;
    }

}
