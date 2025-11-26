package com.sanjoy.auth.dto;

public class ProfileRequest {
    private Integer age;
    private String gender;
    private String phoneNumber;
    private String college;
    private String course;
    private Integer graduationYear;
    private String location;

    private String bio;

    // Getters and setters
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getCollege() { return college; }
    public void setCollege(String college) { this.college = college; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public Integer getGraduationYear() { return graduationYear; }
    public void setGraduationYear(Integer graduationYear) { this.graduationYear = graduationYear; }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
}
