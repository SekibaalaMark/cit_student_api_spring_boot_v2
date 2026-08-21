package com.cit.student_api_v2.student.controller;
import com.cit.student_api_v2.api.response.ApiResponse;
import com.cit.student_api_v2.page.response.PageResponse;
import com.cit.student_api_v2.student.dto.StudentRequest;
import com.cit.student_api_v2.student.dto.StudentResponse;
import com.cit.student_api_v2.student.dto.StudentUpdateRequest;
import com.cit.student_api_v2.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> getStudent(@PathVariable Long id){
        StudentResponse studentResponse =  studentService.findById(id);
        ApiResponse<StudentResponse> apiResponse = new ApiResponse<>("SUCCESS","Student Found",studentResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<StudentResponse>>>  getAllStudents(
            @RequestParam(defaultValue = "1") int offset,
            @RequestParam(defaultValue = "10") int pageSize){
        PageResponse<StudentResponse> data=  studentService.findAll(offset,pageSize);
        ApiResponse<PageResponse<StudentResponse>> apiResponse = new ApiResponse<>("SUCCESS","List of Students",data);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/reg/{registrationNumber}")
    public ResponseEntity<ApiResponse<StudentResponse>>  getStudent(@PathVariable String registrationNumber){
        StudentResponse data =  studentService.findByRegistrationNumber(registrationNumber);
        ApiResponse<StudentResponse> apiResponse = new ApiResponse<>("SUCCESS","Student Returned",data);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }



    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponse>> addStudent(@Valid @RequestBody StudentRequest studentRequest){
        StudentResponse studentResponse =  studentService.save(studentRequest);
        ApiResponse<StudentResponse> apiResponse = new ApiResponse<>("SUCCESS","Student Added Successfully",studentResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentUpdateRequest updateRequest){
        StudentResponse studentResponse =  studentService.updateStudent(id,updateRequest);
        ApiResponse<StudentResponse> apiResponse = new ApiResponse<>("SUCCESS","Student "+id+" Updated Successfully",studentResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable Long id){
        studentService.delete(id);
        ApiResponse<Void> apiResponse = new ApiResponse<>("SUCCESS","Student "+id+" deleted Successfully",null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(apiResponse);
    }
}
