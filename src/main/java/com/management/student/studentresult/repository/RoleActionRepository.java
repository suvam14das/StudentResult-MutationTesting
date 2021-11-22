package com.management.student.studentresult.repository;

import com.management.student.studentresult.dao.Action;
import com.management.student.studentresult.dao.Role;
import com.management.student.studentresult.dao.RoleAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleActionRepository extends JpaRepository<RoleAction, Integer> {

    List<Action> findByRoleAndStatus(Role role, String status);
}
