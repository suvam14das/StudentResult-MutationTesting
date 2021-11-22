package com.management.student.studentresult.vo;

public class ObjectionVO extends MarksVO {

    private String operation;
    private String comments;

    public ObjectionVO() {
        
    }
    
    ObjectionVO(String rollNo, String subjectCode, String subjectName, int year, int term,
                int totalMarks, Double marksObtained, String comments, String operation, String grade) {
        super(rollNo, subjectCode, subjectName, year, term, totalMarks, marksObtained, grade);
            this.comments = comments;
            this.operation = operation;
        }

    @Override
    public String getOperation() {
        return operation;
    }

    @Override
    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}









