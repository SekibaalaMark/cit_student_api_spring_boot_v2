package com.cit.student_api_v2.enrollment.facade;


import com.cit.student_api_v2.enrollment.dto.EnrollmentRequest;
import com.cit.student_api_v2.enrollment.dto.EnrollmentResponse;
import com.cit.student_api_v2.enrollment.mapper.EnrollmentMapper;
import com.cit.student_api_v2.enrollment.model.Enrollment;
import com.cit.student_api_v2.enrollment.repository.EnrollmentRepository;
import com.cit.student_api_v2.student.exception.StudentNotFoundException;
import com.cit.student_api_v2.student.model.Student;
import com.cit.student_api_v2.student.repository.StudentRepository;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentFacade {
    EnrollmentRepository enrollmentRepository;
    EnrollmentMapper enrollmentMapper;
    StudentRepository studentRepository;

    public EnrollmentFacade(EnrollmentRepository enrollmentRepository,
                            EnrollmentMapper enrollmentMapper,
                            StudentRepository studentRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentMapper = enrollmentMapper;
        this.studentRepository = studentRepository;
    }

    public EnrollmentResponse createEnrollment(EnrollmentRequest request){
        Student student = studentRepository.findById(request.studentId())
                .orElseThrow(()-> new StudentNotFoundException("Student not found with ID: " + request.studentId()));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);

        enrollmentRepository.save(enrollment);
        return new EnrollmentResponse(enrollment.getId(),
                enrollment.getStudent().getId(),
                enrollment.getStudent().getRegistrationNumber(),
                enrollment.getStudent().getName(),
                enrollment.getCreatedAt());
    }
}
