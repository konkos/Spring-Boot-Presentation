package gr.uom.opensource.student;

import gr.uom.opensource.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service("StudentServiceImpl")
public class StudentServiceImpl implements StudentService{
    private final List<Student> students;

    @Autowired
    public StudentServiceImpl(){
        this.students = new ArrayList<>();

    }

    public List<Student> getAllStudents(){
        return students;
    }

    public Student addStudent(Student studentToAdd){
        boolean add = false;
        if(students.stream().filter(s-> s.getId()==studentToAdd.getId()).findFirst().isEmpty()){
            this.students.add(studentToAdd);
            add = true;
        }

        if(add)
            return studentToAdd;

        return new Student();
    }

    public Student getStudentById(int id){
        for (Student student : students)
            if(student.getId() == id)
                return student;

        return new Student();
    }

    @Override
    public Student updateStudent(int id, Student updatedStudent) {
        for (Student student : students) {
            if(student.getId()==id){
                if(!student.getFirstName().equals(updatedStudent.getFirstName()) && updatedStudent.getFirstName()!=null)
                    student.setFirstName(updatedStudent.getFirstName());

                if (!student.getLastName().equals(updatedStudent.getLastName()) && updatedStudent.getLastName()!=null)
                    student.setLastName(updatedStudent.getLastName());

                return student;
            }
        }
        return new Student();
    }

    @Override
    public void deleteStudent(int id) {
        Student studentToBeDeleted = null;
        for(Student student:students){
            if(student.getId()==id){
                studentToBeDeleted = student;
            }
        }
        if(studentToBeDeleted!=null)
            students.remove(studentToBeDeleted);

    }

    @Override
    public List<Course> getStudentCourses(Integer id) {
//        List<Registration> registrations = registrationRepository.findCoursesByStudent(id);
//        List<Course> courses = new ArrayList<>();
//
//        for (Registration registration : registrations) {
//            courses.add(registration.getCourse());
//        }
//        return courses;
        try {
            throw new Exception("UNIMPLEMENTED METHOD");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
