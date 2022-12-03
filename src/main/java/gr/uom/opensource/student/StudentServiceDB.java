package gr.uom.opensource.student;

import gr.uom.opensource.course.Course;
import gr.uom.opensource.exceptions.StudentNotFoundException;
import gr.uom.opensource.registration.Registration;
import gr.uom.opensource.registration.RegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("StudentServiceDB")
@Transactional
public class StudentServiceDB implements StudentService {

    private final StudentRepository studentRepository;
    private final RegistrationRepository registrationRepository;

    public StudentServiceDB(StudentRepository studentRepository, RegistrationRepository registrationRepository) {
        this.studentRepository = studentRepository;
        this.registrationRepository = registrationRepository;
    }


    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(Student studentToAdd) {
        return studentRepository.save(studentToAdd);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Student getStudentById(int id) {
        Optional<Student> byId = studentRepository.findById(id); // SELECT * FROM student where student.id=?
        return byId.orElse(new Student());
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Student updateStudent(int id, Student updatedStudent) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        Student student = studentOptional.orElseThrow(() -> new StudentNotFoundException());

        student.setFirstName(updatedStudent.getFirstName() != null ? updatedStudent.getFirstName() : student.getFirstName());
        student.setLastName(updatedStudent.getLastName() != null ? updatedStudent.getLastName() : student.getLastName());
        studentRepository.save(student);
        return student;
    }

    @Override
    public void deleteStudent(int id) {
        if (!studentRepository.existsById(id))
            throw new RuntimeException("Student Not Found");
        studentRepository.deleteById(id);
    }

    @Override
    public List<Course> getStudentCourses(Integer id) {
        List<Registration> registrations = registrationRepository.findCoursesByStudent(id);
        List<Course> courses = new ArrayList<>();

        for (Registration registration : registrations) {
            courses.add(registration.getCourse());
        }
        return courses;
    }
}
