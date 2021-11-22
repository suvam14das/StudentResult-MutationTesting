package com.management.student.studentresult.enums;

public enum QueryParameters {
    ROLL_NUMBER("rollNumber"),
    SUBJECT_CODE("subjectCode"),
    SUBJECT_NAME("subjectName"),
    TERM("term"),
    YEAR("year");

    String param;
    QueryParameters(String param){
        this.param = param;
    }

    public String getParam(){
        return this.param;
    }
}
