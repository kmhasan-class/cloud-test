package bd.edu.seu.studentservice.service;

import bd.edu.seu.studentservice.exception.ResourceAlreadyExistsException;
import bd.edu.seu.studentservice.exception.ResourceDoesNotExistException;
import bd.edu.seu.studentservice.model.Student;
import bd.edu.seu.studentservice.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student insertStudent(Student student) throws ResourceAlreadyExistsException {
        Optional<Student> optionalStudent = studentRepository.findById(student.getId());

        if (optionalStudent.isPresent())
            throw new ResourceAlreadyExistsException(student.getId() + "");

        return studentRepository.save(student);
    }

    public Student updateStudent(long studentId, Student student) throws ResourceDoesNotExistException {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        student.setId(studentId);

        if (optionalStudent.isPresent())
            return studentRepository.save(student);
        else throw new ResourceDoesNotExistException(studentId + "");
    }

    public void deleteAll() {
        studentRepository.deleteAll();
    }
}
