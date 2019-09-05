package bd.edu.seu.studentservice.controller;

import bd.edu.seu.studentservice.exception.ResourceAlreadyExistsException;
import bd.edu.seu.studentservice.model.Student;
import bd.edu.seu.studentservice.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> findAll() {
        List<Student> studentList = studentService.findAll();
        return ResponseEntity.ok(studentList);
    }

    @GetMapping("dummy")
    public ResponseEntity<List<Student>> insertDummyStudents() {
        List<Student> savedStudentList = new ArrayList<>();

        Stream.of(new Student(1001, "Abul", LocalDate.now()),
                new Student(1002, "Babul", LocalDate.now()),
                new Student(1003, "Kabul", LocalDate.now()),
                new Student(1004, "Ratul", LocalDate.now()),
                new Student(1005, "Putul", LocalDate.now()),
                new Student(1006, "Tetul", LocalDate.now())
        ).forEach(student -> {
            try {
                savedStudentList.add(studentService.insertStudent(student));
            } catch (ResourceAlreadyExistsException e) {
                e.printStackTrace();
            }
        });

        return ResponseEntity.ok(savedStudentList);
    }
}
