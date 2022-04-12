package gr.uom.opensource.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gr.uom.opensource.registration.Registration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Course {

    @Id
    private int id;

    private String name;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<Registration> registeredStudents;

    public Course(){}

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }
//
//    public Course(int id){
//        this.id = id;
//    }
//
//    public Course(String name){
//        this.name = name;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Registration> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(Set<Registration> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
