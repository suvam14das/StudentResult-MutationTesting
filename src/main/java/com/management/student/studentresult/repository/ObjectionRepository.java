package com.management.student.studentresult.repository;

import com.management.student.studentresult.dao.Marks;
import com.management.student.studentresult.dao.Objection;
import com.management.student.studentresult.dao.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjectionRepository extends JpaRepository<Objection, Integer>, JpaSpecificationExecutor<Objection>{

    Objection findByMarks(Marks marks);

    Page<Objection> findAllByCreatedBy(User user, Pageable pageable);

    Page<Objection> findAllByResolverId(User user, Pageable pageable);
}
