package dev.patika.SchoolManagementSystem04.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Student {
    //by this @Id annotation spring boot will generate an id to be the primary Key of the object(entity) in the database
    // and By the annotation @GeneratedValue(strategy = GenerationType.IDENTITY)
    // we can specify GenerationType 'TABLE','SEQUENCE','IDENTITY',and we can leave the selection to spring boot by making the type 'AUTO'.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name ;
    private LocalDate birth_date;
    private String address;
    private String gender;
    //@ManyToMany annotation links this entity which is (STUDENT) with the entity defined in down field in this situation 'List<Course>' in the database
    // -- so we have MANY of STUDENTS linked to MANY of COURSES as 'List<Course>'.
    @ManyToMany(mappedBy = "studentList",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Course> CourseList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) && Objects.equals(birth_date, student.birth_date) && Objects.equals(address, student.address) && Objects.equals(gender, student.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birth_date, address, gender);
    }
}