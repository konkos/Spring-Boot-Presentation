package gr.uom.opensource.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gr.uom.opensource.registration.Registration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//CREATE TABLE Student(int id,firstName:varChar...);
@Entity
public class Student {

    @Id
    private int id;

    @Column
    private String firstName;

    @Column
    private String lastName;


    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private Set<Registration> registrations;

    public Student() {}

//    public Student(String firstName, String lastName) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }
//
//    public Student(int id, String firstName, String lastName) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Set<Registration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
