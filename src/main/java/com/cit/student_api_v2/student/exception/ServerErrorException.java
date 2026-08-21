package com.cit.student_api_v2.student.exception;

public class ServerErrorException extends RuntimeException {
    public ServerErrorException(String message){
        super(message);
    }
}
