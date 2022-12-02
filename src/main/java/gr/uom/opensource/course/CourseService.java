package gr.uom.opensource.course;

import gr.uom.opensource.registration.Registration;
import gr.uom.opensource.registration.RegistrationRepository;
import gr.uom.opensource.student.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final RegistrationRepository registrationRepository;

    public CourseService(CourseRepository courseRepository, RegistrationRepository registrationRepository) {
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;
    }


    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course addCourse(Course course) {
        courseRepository.save(course);
        return course;
    }

    @Transactional
    public Course updateCourse(int id, Course updatedCourse) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course Not Found"));

        course.setName(updatedCourse.getName() != null ? updatedCourse.getName() : course.getName());

        courseRepository.save(course);
        return course;
    }

    public Course getCourseById(int id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course Not Found"));
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    public List<Student> getCourseStudents(Integer course_id) {
        List<Registration> registrations = registrationRepository.findStudentsByCourse(course_id);
        List<Student> students = new ArrayList<>();

        if (registrations.isEmpty())
            throw new RuntimeException("NOT FOUND");

        for (Registration registration : registrations) {
            students.add(registration.getStudent());
        }
        return students;
    }
}
