package com.cit.student_api_v2.student.models;

public class Student {
    private String name;
    private String registrationNumber;
    private double cgpa;

    public Student(String name, String registrationNumber, double cgpa) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.cgpa = cgpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }
}
