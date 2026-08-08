package com.cit.student_api_v2.api.response;

import java.time.LocalDateTime;
import java.util.Date;

public class ApiResponse<T> {
    private final String status;
    private final String message;
    private final T data;
    private final LocalDateTime date;

    public ApiResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.date = LocalDateTime.now();
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
