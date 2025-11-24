package com.sanjoy.auth.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destination;
    private Double budget;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")

    private Date endDate;
    private Boolean femaleAllowed;
    private Integer maleCount;
    private Integer femaleCount;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToMany
    @JoinTable(
            name = "trip_members",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore  // ADD THIS: Prevent infinite recursion
    private List<User> members;

    private String status;

    // All getters and setters (same as before)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public Double getBudget() { return budget; }
    public void setBudget(Double budget) { this.budget = budget; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public Boolean getFemaleAllowed() { return femaleAllowed; }
    public void setFemaleAllowed(Boolean femaleAllowed) { this.femaleAllowed = femaleAllowed; }

    public Integer getMaleCount() { return maleCount; }
    public void setMaleCount(Integer maleCount) { this.maleCount = maleCount; }

    public Integer getFemaleCount() { return femaleCount; }
    public void setFemaleCount(Integer femaleCount) { this.femaleCount = femaleCount; }

    public User getCreator() { return creator; }
    public void setCreator(User creator) { this.creator = creator; }

    public List<User> getMembers() { return members; }
    public void setMembers(List<User> members) { this.members = members; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
