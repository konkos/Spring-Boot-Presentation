package gr.uom.opensource.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/student") //localhost:8080/student/*
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        //Dependency Injection
        this.studentService = studentService;
    }

    @GetMapping // GET localhost:8080/student
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")// GET localhost:8080/student/32
    public Student getStudentByID(@PathVariable int id){
        return studentService.getStudentById(id);
    }

    @PostMapping // POST localhost:8080/student
    public Student addStudent(@RequestBody Student student){
        return this.studentService.addStudent(student);
    }

    @PutMapping("/{id}")// PUT localhost:8080/student/32
    public Student updateStudent(@PathVariable int id, @RequestBody Student updatedStudent){
        return studentService.updateStudent(id,updatedStudent);
    }

    @DeleteMapping("/{id}")// DELETE localhost:8080/32
    public void deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);
    }

}
