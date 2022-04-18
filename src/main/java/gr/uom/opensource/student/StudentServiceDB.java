package gr.uom.opensource.student;

import gr.uom.opensource.course.Course;
import gr.uom.opensource.registration.Registration;
import gr.uom.opensource.registration.RegistrationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("StudentServiceDB")
@Transactional
public class StudentServiceDB implements StudentService{

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

    @Override
    public Student getStudentById(int id) {
        Optional<Student> byId = studentRepository.findById(id); // SELECT * FROM student where student.id=?
        return byId.orElse(new Student());
    }

    @Override
    public Student updateStudent(int id, Student updatedStudent) {
        Optional<Student> byId = studentRepository.findById(id);

        if (byId.isEmpty())
            return new Student();

        Student student  = byId.get();

        if(student.getId()==id){
            if(!student.getFirstName().equals(updatedStudent.getFirstName()) && updatedStudent.getFirstName()!=null)
                student.setFirstName(updatedStudent.getFirstName());

            if (!student.getLastName().equals(updatedStudent.getLastName()) && updatedStudent.getLastName()!=null)
                student.setLastName(updatedStudent.getLastName());

            return student;
        }
        return new Student();
    }

    @Override
    public void deleteStudent(int id) {
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
