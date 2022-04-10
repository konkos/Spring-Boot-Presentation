package gr.uom.opensource.student;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    // SELECT * FROM student where firstName=? AND lastName=? OR AnD ...

//    @Query("SELECT * FROM student where firstName=?")
//    Student getStudentByFirstName(String firstName);

    Student findByFirstName(String firstName);

}
