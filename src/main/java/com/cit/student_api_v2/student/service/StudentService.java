package com.cit.student_api_v2.student.service;

import com.cit.student_api_v2.student.dto.StudentRequest;
import com.cit.student_api_v2.student.dto.StudentUpdateRequest;
import com.cit.student_api_v2.student.mapper.StudentMapper;
import com.cit.student_api_v2.student.model.Student;
import com.cit.student_api_v2.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper){
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;

    }
    public Student findByRegistrationNumber(String registrationNumber){
        return studentRepository.findByRegistrationNumber(registrationNumber);
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
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

    public Student findById(Long id){
        return studentRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Transactional
    public Student updateStudent(Long id, StudentUpdateRequest updateRequest) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        studentMapper.updateEntityFromRequest(updateRequest , existingStudent);

        return studentRepository.save(existingStudent);
    }


}
