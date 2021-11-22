package com.management.student.studentresult.dao;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Objection")
public class Objection {

    @Id
    @GeneratedValue
    @Column(name = "obj_id")
    private int objId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "mrk_id")
    private Marks marks;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "resolver_id")
    private User resolverId;
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
        this.createdAt = this.modifiedAt = new Date();
    }

    @PreUpdate
    void modified_at() {
        this.modifiedAt = new Date();
    }

    public Objection(User user, Marks marks) {
        this.user = user;
        this.marks = marks;
    }

    public Objection() {
    }

    public int getObjId() {
        return objId;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Marks getMarks() {
        return marks;
    }

    public void setMarks(Marks marks) {
        this.marks = marks;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getResolverId() {
        return resolverId;
    }

    public void setResolverId(User resolverId) {
        this.resolverId = resolverId;
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
