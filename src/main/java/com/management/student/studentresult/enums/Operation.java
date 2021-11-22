package com.management.student.studentresult.enums;

public enum Operation {

    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE"),
    RESOLVE("RESOLVE"),
    REJECTED("REJECT");

    private String name;

    Operation(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
