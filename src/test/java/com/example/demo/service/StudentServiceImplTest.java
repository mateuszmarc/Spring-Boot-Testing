package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    StudentRepository studentRepository;

    @Test
    void testFistAllStudents() {

        List<Student> students = List.of(
                new Student("Mateusz", "Marcykiewicz"),
                new Student("Maria", "MM")
        );
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> returnedStudents = studentService.listAllStudents();

        verify(studentRepository).findAll();
        assertThat(returnedStudents).hasSize(2);
        assertIterableEquals(returnedStudents, students);
    }

    @Test
    void testFindByFirstName_whenFindByFirstName_thenStudentIsPresent() {

        Student student = new Student("Mateusz", "Marcykiewicz");
        when(studentRepository.findByFirstName("Mateusz")).thenReturn(Optional.of(student));

        Student foundStudent = studentService.findByFirstName("Mateusz");

        verify(studentRepository).findByFirstName("Mateusz");
        assertAll(
                () -> assertThat(foundStudent).isNotNull(),
                () -> assertThat(foundStudent).isEqualTo(student)
        );
    }

    @Test
    void testFindByFirstName_whenFindByFirstName_thenStudentIsNull() {

        when(studentRepository.findByFirstName("Mariusz")).thenReturn(Optional.empty());

        Student foundStudent = studentService.findByFirstName("Mariusz");

        verify(studentRepository).findByFirstName("Mariusz");

        assertNull(foundStudent);
    }

    @Test
    void addStudent() {

        Student student = new Student("Mateusz", "Marcykiewicz");
        when(studentRepository.save(student)).thenReturn(student);

        Student createdStudent = studentService.addStudent(student);

        verify(studentRepository).save(student);

        assertThat(createdStudent).isEqualTo(student);

    }
}