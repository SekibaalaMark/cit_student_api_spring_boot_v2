package com.cit.student_api_v2.enrollment.service;


import com.cit.student_api_v2.api.response.ApiResponse;
import com.cit.student_api_v2.enrollment.dto.EnrollmentRequest;
import com.cit.student_api_v2.enrollment.dto.EnrollmentResponse;
import com.cit.student_api_v2.enrollment.facade.EnrollmentFacade;
import com.cit.student_api_v2.enrollment.model.Enrollment;
import com.cit.student_api_v2.enrollment.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
    private final EnrollmentFacade enrollmentFacade;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, EnrollmentFacade enrollmentFacade) {
        this.enrollmentFacade = enrollmentFacade;
    }

    public ApiResponse<EnrollmentResponse> addEnrollment(EnrollmentRequest enrollmentRequest){
        EnrollmentResponse enrollmentResponse = enrollmentFacade.createEnrollment(enrollmentRequest);
        return new ApiResponse<>("SUCCESS",
                "Enrollment Added Successfully",
                enrollmentResponse
        );

    }
}
