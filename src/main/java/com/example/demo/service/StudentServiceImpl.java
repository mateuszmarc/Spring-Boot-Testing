package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {

    private  StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAllStudents() {
       return studentRepository.findAll();
    }

    @Override
    public Student findByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName).orElse(null);
    }

    @Override
    public Student addStudent(Student student) {
       return studentRepository.create(student);
    }
}
