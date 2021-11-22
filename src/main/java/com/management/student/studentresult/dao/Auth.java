package com.management.student.studentresult.dao;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Auth")
public class Auth {

    @Id
    @GeneratedValue
    @Column(name = "auth_id")
    private int authId;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
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

    public Auth(String email, String password) {
        this.email = email;
        this.password = password;
        this.status = "ACTIVE";
    }

    public Auth() {
    }

    public int getAuthId() {
        return authId;
    }

    public void setAuthId(int authId) {
        this.authId = authId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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