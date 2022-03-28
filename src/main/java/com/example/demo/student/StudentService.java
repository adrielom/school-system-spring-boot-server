package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByName = studentRepository.findStudentByName(student.getName());
        if (studentByName.isPresent()) {
            throw new IllegalStateException("name taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Boolean exists = studentRepository.existsById(id);
        if (!exists)
            throw new IllegalStateException("student doesn't exist");
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name) {
        Student studentInBack = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Student does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(studentInBack.getName(), name)) {
            studentInBack.setName(name);
        }

    }
}
