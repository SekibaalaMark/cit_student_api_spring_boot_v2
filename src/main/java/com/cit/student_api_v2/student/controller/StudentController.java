package com.cit.student_api_v2.student.controller;
import com.cit.student_api_v2.api.response.ApiResponse;
import com.cit.student_api_v2.student.dto.StudentRequest;
import com.cit.student_api_v2.student.dto.StudentResponse;
import com.cit.student_api_v2.student.dto.StudentUpdateRequest;
import com.cit.student_api_v2.student.model.Student;
import com.cit.student_api_v2.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<List<StudentResponse>>>  getAllStudents(){
        List<StudentResponse> data=  studentService.findAll();
        ApiResponse<List<StudentResponse>> apiResponse = new ApiResponse<>("SUCESS","List of Students",data);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/reg/{registrationNumber}")
    public StudentResponse getStudent(@PathVariable String registrationNumber){
        return studentService.findByRegistrationNumber(registrationNumber);
    }

    @GetMapping("/{id}")
    public StudentResponse getStudent(@PathVariable Long id){
        return studentService.findById(id);
    }

    @PostMapping
    public Student addStudent(@Valid @RequestBody StudentRequest studentRequest){
        return studentService.save(studentRequest);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @Valid @RequestBody StudentUpdateRequest updateRequest){
        return studentService.updateStudent(id,updateRequest);
    }


    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.delete(id);
    }
}
