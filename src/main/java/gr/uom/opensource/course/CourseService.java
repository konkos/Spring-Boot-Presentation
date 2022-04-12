package gr.uom.opensource.course;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
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
}
