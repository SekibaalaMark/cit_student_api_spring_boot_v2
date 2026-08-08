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
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentResponse>>>  getAllStudents(){
        List<StudentResponse> data=  studentService.findAll();
        ApiResponse<List<StudentResponse>> body = new ApiResponse<>("SUCCESS","List of Students",data);
        return ResponseEntity.status(200).body(body);
    }

    @GetMapping("/reg/{registrationNumber}")
    public ResponseEntity<ApiResponse<StudentResponse>>  getStudent(@PathVariable String registrationNumber){
        StudentResponse data =  studentService.findByRegistrationNumber(registrationNumber);
        ApiResponse<StudentResponse> body = new ApiResponse<>("SUCCESS","Student Returned",data);
        return ResponseEntity.status(200).body(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>>  getStudent(@PathVariable Long id){
        StudentResponse studentResponse =  studentService.findById(id);
        ApiResponse<StudentResponse> body = new ApiResponse<>("SUCCESS","Student Returned",studentResponse);
        return ResponseEntity.status(200).body(body);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponse>> addStudent(@Valid @RequestBody StudentRequest studentRequest){
        StudentResponse studentResponse =  studentService.save(studentRequest);
        ApiResponse<StudentResponse> body = new ApiResponse<>("SUCCESS","Student Added Successfully",studentResponse);
        return ResponseEntity.status(201).body(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentUpdateRequest updateRequest){
        StudentResponse studentResponse = studentService.updateStudent(id,updateRequest);
        ApiResponse<StudentResponse> apiResponse = new ApiResponse<>("SUCCESS","Student "+ id + " Updated",studentResponse);
        return ResponseEntity.status(200).body(apiResponse);
    }


    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.delete(id);
    }
}
