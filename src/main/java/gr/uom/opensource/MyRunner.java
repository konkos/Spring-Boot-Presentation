package gr.uom.opensource;

import gr.uom.opensource.course.Course;
import gr.uom.opensource.course.CourseRepository;
import gr.uom.opensource.registration.Registration;
import gr.uom.opensource.registration.RegistrationRepository;
import gr.uom.opensource.student.Student;
import gr.uom.opensource.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Component
public class MyRunner implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final RegistrationRepository registrationRepository;
    @Autowired
    public MyRunner(StudentRepository studentRepository, CourseRepository courseRepository, RegistrationRepository registrationRepository){
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class, value = Transactional.TxType.REQUIRES_NEW)
    public void run(String... args) throws Exception {

        System.out.println("START RUNNER");

        for(int i=0;i<10;i++){
            Student student = new Student();
            student.setId(i);
            student.setFirstName("FirstName " + i);
            student.setLastName("LastName " + i);
            studentRepository.save(student);
        }
        for(int i=0;i<10;i++){
            Course course = new Course();
            course.setId(i);
            course.setName("CourseName "+i);
            courseRepository.save(course);
        }

        List<Course> allCourses = courseRepository.findAll();
        List<Student> allStudents = studentRepository.findAll();

        Random random = new Random();

        for(int i=0;i<20;i++){

            Registration registration = new Registration();
            registration.setId(i);
            registration.setCourse(allCourses.get(random.nextInt(allCourses.size())));
            registration.setStudent(allStudents.get(random.nextInt(allCourses.size())));

//            if(i==9) throw new Exception("RANDOM EXCEPTION");

            registrationRepository.save(registration);
        }

        System.out.println("END RUNNER");

        }
}
