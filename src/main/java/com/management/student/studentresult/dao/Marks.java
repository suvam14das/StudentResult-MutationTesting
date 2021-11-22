package com.management.student.studentresult.dao;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Marks")

public class Marks {

    @Id
    @GeneratedValue
    @Column(name = "mrk_id")
    private int mrkId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "sub_id")
    private Subject subject;
    private double score;
    @Column(name = "tot_score")
    private int totScore;
    private int year;
    private int term;
    private String grade;
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

    public Marks(User user, Subject subject, Double score, int totScore, int year, int term, String grade, User moderator) {
        this.user = user;
        this.subject = subject;
        this.score = score;
        this.totScore = totScore;
        this.year = year;
        this.term = term;
        this.grade = grade;
        this.status = "ACTIVE";
        this.createdBy = moderator;
        this.modifiedBy = moderator;
    }

    public Marks(User user, int year, int term) {
        this.user = user;
        this.year = year;
        this.term = term;
    }

    public Marks() {
    }

    public int getMrkId() {
        return mrkId;
    }

    public void setMrkId(int mrkId) {
        this.mrkId = mrkId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public int getTotScore() {
        return totScore;
    }

    public void setTotScore(int totScore) {
        this.totScore = totScore;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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
