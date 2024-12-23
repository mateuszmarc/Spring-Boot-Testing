package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Long, Student> {

    Student findByFirstName(String firstName);


    @Query("select s from Student s where s.firstName like ?1%")
    List<Student> findBySome(String some);
}
