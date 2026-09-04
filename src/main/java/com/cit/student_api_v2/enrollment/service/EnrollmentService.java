package com.cit.student_api_v2.enrollment.service;

import com.cit.student_api_v2.api.response.ApiResponse;
import com.cit.student_api_v2.enrollment.dto.EnrollmentRequest;
import com.cit.student_api_v2.enrollment.dto.EnrollmentResponse;
import com.cit.student_api_v2.enrollment.exception.EnrollmentNotFoundException;
import com.cit.student_api_v2.enrollment.facade.EnrollmentFacade;
import com.cit.student_api_v2.enrollment.mapper.EnrollmentMapper;
import com.cit.student_api_v2.enrollment.model.Enrollment;
import com.cit.student_api_v2.enrollment.repository.EnrollmentRepository;
import com.cit.student_api_v2.page.response.PageResponse;
import com.cit.student_api_v2.student.exception.StudentNotFoundException;
import com.cit.student_api_v2.student.model.Student;
import com.cit.student_api_v2.student.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentFacade enrollmentFacade;
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final StudentRepository studentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, EnrollmentFacade enrollmentFacade, EnrollmentRepository enrollmentRepository1, EnrollmentMapper enrollmentMapper, StudentRepository studentRepository) {
        this.enrollmentFacade = enrollmentFacade;
        this.enrollmentRepository = enrollmentRepository1;
        this.enrollmentMapper = enrollmentMapper;
        this.studentRepository = studentRepository;
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


    public PageResponse<EnrollmentResponse> getAllEnrollments(int page,int pageSize){
        int zeroBasedPage = Math.max(0,page-1);
        Pageable pageable = PageRequest.of(zeroBasedPage,pageSize,Sort.by("id").ascending());
        Page<Enrollment> enrollmentPage = enrollmentRepository.findAll(pageable);
        List<EnrollmentResponse> enrollmentResponseList = enrollmentPage.getContent()
                .stream()
                .map(enrollmentMapper::toResponse)
                .toList();

        return new PageResponse<EnrollmentResponse>(
                enrollmentResponseList,
                page,
                pageSize,
                enrollmentPage.getTotalElements(),
                enrollmentPage.getTotalPages(),
                enrollmentPage.isLast()
        );
    }



    public ApiResponse<EnrollmentResponse> updateEnrollment(Long id, EnrollmentRequest request){
        Enrollment enrollmentToUpdate = enrollmentRepository.findById(id).orElseThrow(() -> new EnrollmentNotFoundException("id: "+id));
        Student student = studentRepository.findById(request.studentId()).orElseThrow(()-> new StudentNotFoundException("id: "+request.studentId()));
        enrollmentToUpdate.setStudent(student);
        EnrollmentResponse enrollmentResponse = enrollmentMapper.toResponse(enrollmentRepository.save(enrollmentToUpdate));

        return new ApiResponse<>(
                "SUCCESS",
                "Enrollment Updated Successfully",
                enrollmentResponse
        );
    }

    @Transactional
    public ApiResponse<Void> unEnrollStudent(Long id){
        int deletedRows = enrollmentRepository.deleteEnrollmentById(id);
        if(deletedRows ==0){
            return new ApiResponse<>("ERROR","Enrollment Id Unknown",null);
        }
        return new ApiResponse<>("SUCCESS","Student Unenrolled Successfully",null);
    }
}
