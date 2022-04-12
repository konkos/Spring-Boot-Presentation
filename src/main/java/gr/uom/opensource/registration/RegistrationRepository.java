package gr.uom.opensource.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
    @Query("select r from Registration r where r.student.id = ?1")
    List<Registration> findCoursesByStudent(@NonNull int student_id);
}
