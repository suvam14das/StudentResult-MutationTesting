package com.management.student.studentresult.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.management.student.studentresult.dao.Marks;
import com.management.student.studentresult.dao.Subject;
import com.management.student.studentresult.dao.User;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Integer>, JpaSpecificationExecutor<Marks> {

	Marks findByUserAndSubject(User user, Subject subject);

	Marks findByUserAndSubjectAndTermAndYear(User user, Subject subject, int term, int year);
}
