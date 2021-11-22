package com.management.student.studentresult.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.management.student.studentresult.dao.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
	
	public Subject findBySubCode(String subCode);

	@Query("SELECT DISTINCT c.term FROM Subject c")
	public List<String> findDistinctByTerm();

	@Query(value = "SELECT c.sub_code, c.name FROM subject c" , nativeQuery = true)
	public List<String> findSubjectByCodeName();

}
