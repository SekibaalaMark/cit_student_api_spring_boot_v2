package com.cit.student_api_v2.enrollment.exception;

public class EnrollmentNotFoundException extends RuntimeException {
    public EnrollmentNotFoundException(String message){
        super(message);
    }
}