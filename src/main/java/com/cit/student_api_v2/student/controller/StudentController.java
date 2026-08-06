package com.cit.student_api_v2.student.controller;
import com.cit.student_api_v2.student.model.Student;
import com.cit.student_api_v2.student.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.findAll();
    }

    @GetMapping("/{registrationNumber}")
    public Student getStudent(@PathVariable String registrationNumber){
        return studentService.findByRegistrationNumber(registrationNumber);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        studentService.save(student);
        return student;
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent){
        return studentService.updateStudent(id,updatedStudent);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.delete(id);
    }
}
