package com.example.demo.service;

import com.example.demo.model.Student;

import java.util.List;

public interface StudentService {


    List<Student> listAllStudents();


    Student findByFirstName(String firstName);


    Student addStudent(Student student);
}
