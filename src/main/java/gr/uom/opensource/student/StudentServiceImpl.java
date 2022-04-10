package gr.uom.opensource.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    private List<Student> students;

//    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository){
        this.students = new ArrayList<>();
//        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents(){
//        Iterable<Student> all = studentRepository.findAll();
//        List<Student> students = new ArrayList<>();
//
//        all.forEach(st -> students.add(st));

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
        else
            return new Student();

//        return studentRepository.save(studentToAdd);
    }

    public Student getStudentById(int id){
        for (Student student : students)
            if(student.getId() == id)
                return student;
//        Optional<Student> byId = studentRepository.findById(id); // SELECT * FROM student where student.id=?
//        return byId.orElse(new Student());
        return new Student();
    }

    @Override
    public Student updateStudent(int id, Student updatedStudent) {
//        Optional<Student> byId = studentRepository.findById(id);
//
//        if (byId.isEmpty())
//            return new Student();
//
//        Student student  = byId.get();

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

//        studentRepository.deleteById(id);
    }
}
