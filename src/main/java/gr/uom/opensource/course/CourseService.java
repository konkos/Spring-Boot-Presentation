package gr.uom.opensource.course;

import gr.uom.opensource.registration.Registration;
import gr.uom.opensource.registration.RegistrationRepository;
import gr.uom.opensource.student.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final RegistrationRepository registrationRepository;

    public CourseService(CourseRepository courseRepository, RegistrationRepository registrationRepository){
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;
    }


    public List<Course> getAllCourses(){
        Iterable<Course> all = courseRepository.findAll();
        List courses = new ArrayList();

        all.forEach(courses::add);
        return courses;
    }

    public Course addCourse(Course course){
        courseRepository.save(course);
        return course;
    }

    public Course updateCourse(int id, Course updatedCourse) {
        Optional<Course> byId = courseRepository.findById(id);

        if (byId.isEmpty())
            return new Course();

        Course course = byId.get();

        if(!course.getName().equals(updatedCourse.getName())) {
            course.setName(updatedCourse.getName());
        }

        courseRepository.save(course);
        return course;
    }

    public Course getCourseById(int id) {
        return courseRepository.findById(id).orElse(new Course());
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    public List<Student> getCourseStudents(Integer course_id) {
        List<Registration> registrations = registrationRepository.findStudentsByCourse(course_id);
        List<Student> students = new ArrayList<>();

        for (Registration registration : registrations) {
            students.add(registration.getStudent());
        }
        return students;
    }
}
