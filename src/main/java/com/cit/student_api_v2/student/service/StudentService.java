package com.cit.student_api_v2.student.service;

import com.cit.student_api_v2.student.model.Student;
import com.cit.student_api_v2.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    public Student findByRegistrationNumber(String registrationNumber){
        return studentRepository.findByRegistrationNumber(registrationNumber);
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public void save(Student student){
        studentRepository.save(student);
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
    public Student updateStudent(Long id, Student updatedDetails) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if(updatedDetails.getName() != null && !updatedDetails.getName().isBlank()){
            existing.setName(updatedDetails.getName());
        } else if (updatedDetails.getRegistrationNumber() != null && !updatedDetails.getRegistrationNumber().isBlank()){
            existing.setRegistrationNumber(updatedDetails.getRegistrationNumber());
        } else if (updatedDetails.getCgpa() != null) {
            existing.setCgpa(updatedDetails.getCgpa());
        }
        return studentRepository.save(existing);
    }


}
