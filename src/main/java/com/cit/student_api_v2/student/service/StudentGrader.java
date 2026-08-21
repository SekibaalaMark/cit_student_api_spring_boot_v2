package com.cit.student_api_v2.student.service;
import org.springframework.stereotype.Service;

@Service
public class StudentGrader {
    public Character grade(Double average){
        if(average>=80){
            return 'A';
        } else if (average>=70) {
            return 'B';
        } else if (average>=60) {
            return 'C';
        } else if (average>=50) {
            return 'D';
        }else{
            return 'F';
        }
    }
}
