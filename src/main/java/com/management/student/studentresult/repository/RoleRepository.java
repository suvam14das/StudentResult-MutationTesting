package com.management.student.studentresult.repository;

import com.management.student.studentresult.dao.Role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

	Optional<Role> findByName(String name);
}
