package gr.uom.opensource.registration;

import gr.uom.opensource.course.Course;
import gr.uom.opensource.course.CourseRepository;
import gr.uom.opensource.student.Student;
import gr.uom.opensource.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationRepository registrationRepository;

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public RegistrationController(RegistrationRepository registrationRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.registrationRepository = registrationRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    @PostMapping
    public void addStudentToCourse(@RequestParam Long id, @RequestParam Integer student_id, @RequestParam Integer course_id) throws Exception {
        Optional<Student> studentOptional = studentRepository.findById(student_id);
        Optional<Course> courseOptional = courseRepository.findById(course_id);


        System.out.println(studentOptional);
        System.out.println(courseOptional);

        if(studentOptional.isEmpty() || courseOptional.isEmpty())
            return;

        Course course = courseOptional.get();
        Student student = studentOptional.get();
        Registration registration = new Registration().setCourse(course).setId(id).setStudent(student);
        System.out.println(registration);
        registrationRepository.save(registration);
    }

    @GetMapping
    public List<Registration> getRegistrations(){
        return registrationRepository.findAll();
    }

    @PutMapping("/grade/{student_id}/{course_id}/{grade}")
    public Student updateGrade(@PathVariable int student_id, @PathVariable int course_id, @PathVariable double grade){
        Student studentOptional = studentRepository.findById(student_id).get();
        Course courseOptional = courseRepository.findById(course_id).get();

        List<Registration> coursesByStudent = registrationRepository.findCoursesByStudent(student_id);
        Registration registration1 = coursesByStudent.stream().filter(registration -> registration.getCourse().getId() == course_id).findFirst().get();
        if(grade>0 && grade<=10) registration1.setGrade(grade);
        registrationRepository.save(registration1);
        return registration1.getStudent();
    }
}
