package com.example.demo.repository;

import com.example.demo.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testFindByFirstName_thenReturnStudent() {

        Student john = new Student("John", "Doe");
        entityManager.persist(john);

        Optional<Student> student = studentRepository.findByFirstName("John");

        assertThat(student).isPresent();
        assertThat(student.get().getFirstName()).isEqualTo(john.getFirstName());
    }

    @Test
    void testFindByFirstName_thenReturnEmpty() {

        Student john = new Student("John", "Doe");
        entityManager.persist(john);

        Optional<Student> student = studentRepository.findByFirstName("Mark");

        assertThat(student).isEmpty();
    }

    @Test
    void testFindBySome_whenFindByNameInDatabase_thenListNotEmpty() {

        Student john = new Student("John", "Doe");
        entityManager.persist(john);

        List<Student> students = studentRepository.findBySome("Jo");

        assertThat(students).isNotEmpty();
        assertThat(students.get(0).getFirstName()).isEqualTo("John");
    }

    @Test
    void testFindBySome_whenFindByNameInDatabase_thenListEmpty() {

        Student john = new Student("John", "Doe");
        entityManager.persist(john);

        List<Student> students = studentRepository.findBySome("Ma");

        assertThat(students).isEmpty();
    }
}