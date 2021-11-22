package com.management.student.studentresult.dao;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Role_Action")

public class RoleAction {

    @Id
    @GeneratedValue
    @Column(name = "rlac_id")
    private int rlacId;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "act_id")
    private Action action;
    private String access;
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

    public RoleAction(Role role, Action action, String access) {
        this.role = role;
        this.action = action;
        this.access = access;
        this.status = "ACTIVE";
    }

    public RoleAction() {
    }

    public int getRlacId() {
        return rlacId;
    }

    public void setRlacId(int rlacId) {
        this.rlacId = rlacId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
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
