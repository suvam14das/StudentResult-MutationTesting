package com.management.student.studentresult.repository;

import com.management.student.studentresult.dao.Auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Integer> {

	public Optional<Auth> findByEmail(String email);
}
