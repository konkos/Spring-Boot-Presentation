package gr.uom.opensource.student;

import gr.uom.opensource.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/student") //localhost:8080/student/*
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(@Qualifier(value = "StudentServiceDB") StudentService studentService){
        //Dependency Injection
        this.studentService = studentService;
    }

    //start demo using /student/allStudents
    @GetMapping // GET localhost:8080/student
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")// GET localhost:8080/student/2
    public Student getStudentByID(@PathVariable int id){
        return studentService.getStudentById(id);
    }

    //start demo using /student/addStudents
    @PostMapping // POST localhost:8080/student
    public Student addStudent(@RequestBody Student student){
        return this.studentService.addStudent(student);
    }

    @PutMapping()// PUT localhost:8080/student
    public Student updateStudent(@RequestParam int id, @RequestBody Student updatedStudent){
        return studentService.updateStudent(id,updatedStudent);
    }

    @DeleteMapping("/{id}")// DELETE localhost:8080/2
    public void deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);
    }

    @GetMapping("/{student_id}/courses")
    public List<Course> getStudentCourses(@PathVariable Integer student_id){
        return studentService.getStudentCourses(student_id);
    }

}
