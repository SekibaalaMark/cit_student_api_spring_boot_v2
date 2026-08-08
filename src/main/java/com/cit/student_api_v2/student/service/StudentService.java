package com.cit.student_api_v2.student.service;

import com.cit.student_api_v2.student.dto.StudentRequest;
import com.cit.student_api_v2.student.dto.StudentResponse;
import com.cit.student_api_v2.student.dto.StudentUpdateRequest;
import com.cit.student_api_v2.student.mapper.StudentMapper;
import com.cit.student_api_v2.student.model.Student;
import com.cit.student_api_v2.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        Student student = studentRepository.findByRegistrationNumber(registrationNumber);
        return studentMapper.toResponse(student);
    }

    public Character grade(Long id){
        Student student = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Studnt Not Found"));
        return studentGrader.grade(student.getCgpa());
    }

    public List<StudentResponse> findAll(){
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponse)
                .toList();
    }

    public Student save(StudentRequest studentRequest){
        Student student = studentMapper.toEntity(studentRequest);
        return  studentRepository.save(student);
    }

    public void deleteByRegistrationNumber(String registrationNumber){
        studentRepository.deleteByRegistrationNumber(registrationNumber);
    }

    public void delete(Long id){
        studentRepository.deleteById(id);
    }


    public StudentResponse findById(Long id){
        Student student = studentRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Student not found"));

        return studentMapper.toResponse(student);

    }

    @Transactional
    public Student updateStudent(Long id, StudentUpdateRequest updateRequest) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        studentMapper.updateEntityFromRequest(updateRequest , existingStudent);

        return studentRepository.save(existingStudent);
    }
}
