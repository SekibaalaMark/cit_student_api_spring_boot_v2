package com.cit.student_api_v2.student.service;

import com.cit.student_api_v2.page.response.PageResponse;
import com.cit.student_api_v2.student.dto.StudentRequest;
import com.cit.student_api_v2.student.dto.StudentResponse;
import com.cit.student_api_v2.student.dto.StudentUpdateRequest;
import com.cit.student_api_v2.student.exception.StudentNotFoundException;
import com.cit.student_api_v2.student.mapper.StudentMapper;
import com.cit.student_api_v2.student.model.Student;
import com.cit.student_api_v2.student.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final StudentGrader studentGrader;
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, StudentGrader studentGrader){
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.studentGrader = studentGrader;

    }
    public StudentResponse findByRegistrationNumber(String registrationNumber){
        Student student = studentRepository.findByRegistrationNumber(registrationNumber).orElseThrow(()-> new StudentNotFoundException("Student with registration Number "+ registrationNumber + " Not Found"));
        return studentMapper.toResponse(student);
    }

    public Character grade(Long id){
        Student student = studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException("Student with id: " + id + "Not Found"));
        return studentGrader.grade(student.getCgpa());
    }

    public PageResponse<StudentResponse> findAll(int offset, int pageSize){
        int zeroBasedPage = Math.max(0,offset -1);
        Pageable pageable = PageRequest.of(zeroBasedPage,pageSize, Sort.by("cgpa").descending());
        Page<Student> studentPage = studentRepository.findAll(pageable);
        List<StudentResponse> studentResponseList = studentPage.getContent()
                .stream()
                .map(studentMapper::toResponse)
                .toList();

        return new PageResponse<StudentResponse>(
                studentResponseList,
                zeroBasedPage,studentPage.getSize(),
                studentPage.getTotalElements(),
                studentPage.getTotalPages(),
                studentPage.isLast()
        );
    }

    public StudentResponse save(StudentRequest studentRequest){
        Student student = studentMapper.toEntity(studentRequest);
        Student savedStudent =  studentRepository.save(student);
        return studentMapper.toResponse(savedStudent);

    }

    public void deleteByRegistrationNumber(String registrationNumber){
        studentRepository.findByRegistrationNumber(registrationNumber).
                orElseThrow(() -> new StudentNotFoundException("Student with RegNo.: " + registrationNumber + " Not found"));
        studentRepository.deleteByRegistrationNumber(registrationNumber);
    }

    public void delete(Long id){
        studentRepository.findById(id).
                orElseThrow(() -> new StudentNotFoundException("Student with Id: " + id + " Not found"));
        studentRepository.deleteById(id);
    }


    public StudentResponse findById(Long id){
        Student student = studentRepository.findById(id).
                orElseThrow(() -> new StudentNotFoundException("Student with Id: " + id + " Not found"));
        return studentMapper.toResponse(student);

    }

    public StudentResponse updateStudent(Long id, StudentUpdateRequest updateRequest) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id: " + id + "Not Found"));

        studentMapper.updateEntityFromRequest(updateRequest , existingStudent);
        Student student = studentRepository.save(existingStudent);
        return studentMapper.toResponse(student);
    }
}
