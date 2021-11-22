package com.management.student.studentresult.vo;

import com.management.student.studentresult.enums.Operation;

public class MarksVO {

	private String rollNo;
	private String subjectCode;
	private String subjectName;
	private int year;
	private int term;
	private int totalMarks;
	private Double marksObtained;
	private String grade;
	private String operation;

	public MarksVO() {
	}

	public MarksVO(String rollNo, String subjectCode, String subjectName, int year, int term, int totalMarks,
			Double marksObtained, String grade) {
		this.rollNo = rollNo;
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
		this.year = year;
		this.term = term;
		this.totalMarks = totalMarks;
		this.marksObtained = marksObtained;
		this.grade = grade;
	}

	public MarksVO(String rollNo, String subjectCode, String subjectName, int year, int term, int totalMarks,
				   Double marksObtained, String grade, String operation) {
		this(rollNo, subjectCode, subjectName, year, term, totalMarks, marksObtained, grade);
		this.operation = operation;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public Double getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(Double marksObtained) {
		this.marksObtained = marksObtained;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return "MarksVO [rollNo=" + rollNo + ", subjectCode=" + subjectCode + ", subjectName=" + subjectName + ", year="
				+ year + ", term=" + term + ", totalMarks=" + totalMarks + ", marksObtained=" + marksObtained
				+ ", grade=" + grade + "]";
	}

}
