package gr.uom.opensource.student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    Student addStudent(Student studentToAdd);

    Student getStudentById(int id);

    Student updateStudent(int id, Student updatedStudent);

    void deleteStudent(int id);
}
