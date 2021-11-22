package com.management.student.studentresult.specs;

import com.management.student.studentresult.dao.Marks;
import com.management.student.studentresult.dao.Subject;
import com.management.student.studentresult.dao.User;
import org.springframework.data.jpa.domain.Specification;

public class MarksSpecs {

    public static Specification<Marks> marksTermEquals(int term){
        return (root, query, builder) ->
                term == 0 ?
                        builder.conjunction() :
                        builder.equal(root.get("term"), term);
    }

    public static Specification<Marks> marksYearEquals(int year){
        return (root, query, builder) ->
                year == 0 ?
                        builder.conjunction() :
                        builder.equal(root.get("year"), year);
    }

    public static Specification<Marks> marksUserEquals(User user, String rollNumber){
        return (root, query, builder) ->
                user == null && (rollNumber == null || "".equalsIgnoreCase(rollNumber)) ?
                        builder.conjunction() :
                        builder.equal(root.get("user"), user);
    }

    public static Specification<Marks> marksSubjectEquals(Subject subject, String subjectCode){
        return (root, query, builder) ->
                subject == null && (subjectCode == null || "".equalsIgnoreCase(subjectCode))?
                        builder.conjunction() :
                        builder.equal(root.get("subject"), subject);
    }

    public static Specification<Marks> subjectNameEquals(String name){
        return (root, query, builder) ->
                name == null || "".equalsIgnoreCase(name) ?
                        builder.conjunction() :
                        builder.equal(root.get("name"), name);
    }

    public static Specification<Marks> marksStatusActive() {
        return (root, query, builder) ->
                builder.equal(root.get("status"), "ACTIVE");
    }
}
