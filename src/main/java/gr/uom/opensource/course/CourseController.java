package gr.uom.opensource.course;

import gr.uom.opensource.student.Student;
import gr.uom.opensource.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping // GET localhost:8080/course
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")// GET localhost:8080/course/32
    public Course getCourseByID(@PathVariable int id){
        return courseService.getCourseById(id);
    }

    @PostMapping // POST localhost:8080/course
    public Course addCourse(@RequestBody Course course){
        return this.courseService.addCourse(course);
    }

    @PutMapping// PUT localhost:8080/course/?id=32
    public Course updateCourse(@RequestParam int id, @RequestBody Course updatedCourse){
        return courseService.updateCourse(id,updatedCourse);
    }

    @DeleteMapping("/{id}")// DELETE localhost:8080/32
    public void deleteCourse(@PathVariable int id){
        courseService.deleteCourse(id);
    }

    @GetMapping("/{course_id}/students")
    public List<Student> getCourseStudents(@PathVariable Integer course_id){
        return courseService.getCourseStudents(course_id);
    }

}
