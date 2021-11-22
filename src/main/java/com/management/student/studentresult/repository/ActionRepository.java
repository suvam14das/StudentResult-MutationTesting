package com.management.student.studentresult.repository;

import com.management.student.studentresult.dao.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends JpaRepository<Action, Integer> {
}
