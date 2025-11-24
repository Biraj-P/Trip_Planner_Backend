package com.sanjoy.auth.dto;

import java.util.Date;

public class TripDTO {
    private Long id;
    private String destination;
    private Double budget;
    private Date startDate;
    private Date endDate;
    private Boolean femaleAllowed;
    private Integer maleCount;
    private Integer femaleCount;
    private String status;

    // Creator info (without circular reference)
    private Long creatorId;
    private String creatorName;
    private String creatorEmail;

    // Constructor
    public TripDTO(Long id, String destination, Double budget, Date startDate,
                   Date endDate, Boolean femaleAllowed, Integer maleCount,
                   Integer femaleCount, String status, Long creatorId,
                   String creatorName, String creatorEmail) {
        this.id = id;
        this.destination = destination;
        this.budget = budget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.femaleAllowed = femaleAllowed;
        this.maleCount = maleCount;
        this.femaleCount = femaleCount;
        this.status = status;
        this.creatorId = creatorId;
        this.creatorName = creatorName;
        this.creatorEmail = creatorEmail;
    }

    // Getters and Setters
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

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getCreatorId() { return creatorId; }
    public void setCreatorId(Long creatorId) { this.creatorId = creatorId; }

    public String getCreatorName() { return creatorName; }
    public void setCreatorName(String creatorName) { this.creatorName = creatorName; }

    public String getCreatorEmail() { return creatorEmail; }
    public void setCreatorEmail(String creatorEmail) { this.creatorEmail = creatorEmail; }
}
