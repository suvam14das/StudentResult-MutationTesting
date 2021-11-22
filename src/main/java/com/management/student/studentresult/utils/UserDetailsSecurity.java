package com.management.student.studentresult.utils;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.management.student.studentresult.dao.User;


public class UserDetailsSecurity implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String name;
	private boolean isEnabled;
	private String role;
	private ArrayList<GrantedAuthority> authorities;
	

	public UserDetailsSecurity(User user) {
		this.username= user.getExtId();
		this.password=user.getAuth().getPassword();
		this.name=user.getName();
		if(user.getStatus().equals("ACTIVE"))
			this.isEnabled=true;
		else this.isEnabled=false;
		this.role=user.getRole().getName();
		this.authorities=new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return this.isEnabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.isEnabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return this.isEnabled;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.isEnabled;
	}

}
