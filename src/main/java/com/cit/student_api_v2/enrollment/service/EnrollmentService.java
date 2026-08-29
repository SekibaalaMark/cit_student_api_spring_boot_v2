package com.cit.student_api_v2.enrollment.service;

import com.cit.student_api_v2.api.response.ApiResponse;
import com.cit.student_api_v2.enrollment.dto.EnrollmentRequest;
import com.cit.student_api_v2.enrollment.dto.EnrollmentResponse;
import com.cit.student_api_v2.enrollment.exception.EnrollmentNotFoundException;
import com.cit.student_api_v2.enrollment.facade.EnrollmentFacade;
import com.cit.student_api_v2.enrollment.mapper.EnrollmentMapper;
import com.cit.student_api_v2.enrollment.model.Enrollment;
import com.cit.student_api_v2.enrollment.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentService {
    private final EnrollmentFacade enrollmentFacade;
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, EnrollmentFacade enrollmentFacade, EnrollmentRepository enrollmentRepository1, EnrollmentMapper enrollmentMapper) {
        this.enrollmentFacade = enrollmentFacade;
        this.enrollmentRepository = enrollmentRepository1;
        this.enrollmentMapper = enrollmentMapper;
    }

    public ApiResponse<EnrollmentResponse> addEnrollment(EnrollmentRequest enrollmentRequest){
        EnrollmentResponse enrollmentResponse = enrollmentFacade.createEnrollment(enrollmentRequest);
        return new ApiResponse<>("SUCCESS",
                "Enrollment Added Successfully",
                enrollmentResponse
        );
    }


    //@Transactional(readOnly = true)

    public ApiResponse<EnrollmentResponse> getEnrollmentSummaryById(Long id){
        Enrollment enrollment = enrollmentRepository.findByIdWithSummary(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment with "+ id + " not Found"));
        EnrollmentResponse enrollmentResponse = enrollmentMapper.toResponse(enrollment);
        return new ApiResponse<>("SUCCESS","Enrollment item",enrollmentResponse);
    }
}
