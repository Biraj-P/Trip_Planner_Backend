package com.sanjoy.auth.dto;

public class ProfileResponse {
    private Long id;
    private String name;
    private String email;
    private String picture;
    private Integer age;
    private String gender;
    private String phoneNumber;
    private String college;
    private String course;
    private Integer graduationYear;
    private String location;
    private String bio;

    // Constructor
    public ProfileResponse(Long id, String name, String email, String picture,
                           Integer age, String gender, String phoneNumber,
                           String college, String course, Integer graduationYear, String location, String bio) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.college = college;
        this.course = course;
        this.graduationYear = graduationYear;
        this.location = location;
        this.bio = bio;
    }

    // Getters and setters (all fields)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPicture() { return picture; }
    public void setPicture(String picture) { this.picture = picture; }

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

    public  String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
}
