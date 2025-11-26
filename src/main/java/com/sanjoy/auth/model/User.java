package com.sanjoy.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String picture;

    // Profile fields
    private Integer age;
    private String gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String college;
    private String course;

    @Column(name = "graduation_year")
    private Integer graduationYear;

    @Column
    private String location;

    @Column(length = 500)
    private String bio;

    // If you have any Trip relationships in User, add @JsonIgnore
    @OneToMany(mappedBy = "creator")
    @JsonIgnore  // ADD THIS
    private List<Trip> createdTrips;

    @ManyToMany(mappedBy = "members")
    @JsonIgnore  // ADD THIS
    private List<Trip> joinedTrips;

    public User() {}

    public User(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    // All getters and setters (including new profile fields)
    public Long getId() { return id; }

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

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
}
