package com.cit.student_api_v2.enrollment.exception;




import com.cit.student_api_v2.api.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EnrollmentGlobalExceptionHandler {

    @ExceptionHandler(EnrollmentNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleStudentNotFound(EnrollmentNotFoundException ex){
        ApiResponse<Void> apiResponse = new ApiResponse<>("ERROR",ex.getMessage(),null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);

    }

}

