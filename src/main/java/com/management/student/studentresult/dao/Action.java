package com.management.student.studentresult.dao;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Action")

public class Action {

    @Id
    @GeneratedValue
    @Column(name = "act_id")
    private int actId;
    private String name;
    private String status;
    @ManyToOne
    @JoinColumn(name="created_by")
    private User createdBy;
    @Column(name = "created_at")
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name="modified_by")
    private User modifiedBy;
    @Column(name = "modified_at")
    private Date modifiedAt;

    @PrePersist
    void created_at() {
        this.status = "ACTIVE";
        this.createdAt = this.modifiedAt = new Date();
    }

    @PreUpdate
    void modified_at() {
        this.modifiedAt = new Date();
    }

    public Action(String name) {
        this.name = name;
        this.status = "ACTIVE";
    }

    public Action() {
    }

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
