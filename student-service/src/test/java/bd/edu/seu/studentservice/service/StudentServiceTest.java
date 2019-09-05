package bd.edu.seu.studentservice.service;

import bd.edu.seu.studentservice.exception.ResourceAlreadyExistsException;
import bd.edu.seu.studentservice.exception.ResourceDoesNotExistException;
import bd.edu.seu.studentservice.model.Student;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Autowired
    private StudentService studentService;

    @Before
    public void deleteAll() {
        studentService.deleteAll();
    }

    @Test
    public void testInsertStudentThatDoesNotExist() throws ResourceAlreadyExistsException {
        Student student = new Student(1234, "John Doe", LocalDate.now());

        Student savedStudent = studentService.insertStudent(student);

        assertEquals(student.getId(), savedStudent.getId());
        assertEquals(student.getName(), savedStudent.getName());
        assertEquals(student.getDateOfBirth(), savedStudent.getDateOfBirth());
    }

    @Test
    public void testInsertStudentThatAlreadyExists() throws ResourceAlreadyExistsException {
        Student savedStudent = studentService.insertStudent(new Student(1234, "John Doe", LocalDate.now()));
        assertEquals(1234, savedStudent.getId());
        assertEquals("John Doe", savedStudent.getName());
        assertEquals(LocalDate.now(), savedStudent.getDateOfBirth());

        exceptionRule.expect(ResourceAlreadyExistsException.class);
        savedStudent = studentService.insertStudent(new Student(1234, "John Doe", LocalDate.now()));
    }

    @Test
    public void testUpdateStudentThatAlreadyExists() throws ResourceAlreadyExistsException, ResourceDoesNotExistException {
        Student savedStudent;

        savedStudent = studentService.insertStudent(new Student(1234, "John Doe", LocalDate.now()));
        assertNotNull(savedStudent);

        savedStudent = studentService.updateStudent(1234, new Student(1234, "Jane Doe", LocalDate.now()));
        assertNotNull(savedStudent);
        assertEquals(1234, savedStudent.getId());
        assertEquals("Jane Doe", savedStudent.getName());
        assertEquals(LocalDate.now(), savedStudent.getDateOfBirth());
    }

    @Test
    public void testUpdateStudentThatDoesNotExists() throws ResourceAlreadyExistsException, ResourceDoesNotExistException {
        Student savedStudent;

        savedStudent = studentService.insertStudent(new Student(1234, "John Doe", LocalDate.now()));
        assertNotNull(savedStudent);

        exceptionRule.expect(ResourceDoesNotExistException.class);
        savedStudent = studentService.updateStudent(1235, new Student(1234, "Jane Doe", LocalDate.now()));

        assertNotNull(savedStudent);
        assertEquals(1234, savedStudent.getId());
        assertEquals("Jane Doe", savedStudent.getName());
        assertEquals(LocalDate.now(), savedStudent.getDateOfBirth());
    }

    @Test
    public void testUpdateStudentThatChangesId() throws ResourceAlreadyExistsException, ResourceDoesNotExistException {
        Student savedStudent;

        savedStudent = studentService.insertStudent(new Student(1234, "John Doe", LocalDate.now()));
        assertNotNull(savedStudent);

        savedStudent = studentService.updateStudent(1234, new Student(1235, "Jane Doe", LocalDate.now()));

        assertNotNull(savedStudent);
        assertEquals(1234, savedStudent.getId());
        assertEquals("Jane Doe", savedStudent.getName());
        assertEquals(LocalDate.now(), savedStudent.getDateOfBirth());
    }
}
