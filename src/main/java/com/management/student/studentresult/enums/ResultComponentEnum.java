package com.management.student.studentresult.enums;

public enum ResultComponentEnum {
    BASIC_INFO_SECTION("BASIC DETAILS"),
    BASIC_INFO_NAME("Name :"),
    BASIC_INFO_ROLLNUMBER("Roll Number :"),
    BASIC_INFO_DOB("Date of Birth :"),
    BASIC_INFO_CONTACT("Contact Number :"),
    BASIC_INFO_ADDRESS("Address :"),
    BASIC_INFO_EMAIL("Email :"),

    MARKS_INFO_SECTION("MARKS"),
    MARKS_INFO_ROLL("Roll Number"),
    MARKS_INFO_SUBJECT("Subject"),
    MARKS_INFO_MARKSOBTAINED("Marks Obtained"),
    MARKS_INFO_TOTMARKS("Total Marks"),
    MARKS_INFO_GRADE("Grade");

    private String componentName;

    ResultComponentEnum(String componentName){
        this.componentName = componentName;
    }

    public String getComponentName(){
        return this.componentName;
    }
}
