package com.management.student.studentresult.vo;

import java.util.Date;

public class UserDetails {

    private String name;
    private String gender;
    private String contactno;
    private String role;
    private String extId;
    private Date dob;
    private String address;
    private String email;
    private String password;
    private int roleId;

    public UserDetails() {
    }

    public UserDetails(String name, String extId, Date dob, String address, String contactno, String email, String password, int roleId) {
        this.name = name;
        this.extId = extId;
        this.dob = dob;
        this.address = address;
        this.contactno = contactno;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getExtId() {
		return extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

    
}
