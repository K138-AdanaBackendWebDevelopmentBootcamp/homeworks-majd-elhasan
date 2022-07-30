package dev.patika.SchoolManagementSystem04.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
//Entities in JPA are nothing but POJOs representing data that can be persisted to the database.
// An entity represents a table stored in a database. Every instance of an entity represents a row in the table.
//In order to do this, we should define an entity so that JPA is aware of it.
//      So let's define it by making use of the @Entity annotation. We must specify this annotation at the class level.
//      We must also ensure that the entity has a no-arg constructor and a primary key:

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Course {
    //by this @Id annotation spring boot will generate an id to be the primary Key of the object(entity) in the database
    // and By the annotation @GeneratedValue(strategy = GenerationType.IDENTITY)
    // we can specify GenerationType 'TABLE','SEQUENCE','IDENTITY',and we can leave the selection to spring boot by making the type 'AUTO'.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String courseName;
    private String courseCode;
    private int creditScore;
    //@ManyToOne annotation links this entity which is (COURSE) with the entity defined in down field in this situation Instructor in the database
    // -- so we have MANY of COURSE linked to ONE INSTRUCTOR.
    @ManyToOne(cascade=CascadeType.ALL)
    private Instructor instructor;
    // if we don't use @JsonIdentityInfo annotation we can use @JsonIgnore to not show up the instructor entity inside Course entity ,
    // to avoid the StackOverFlow error whereas we have a nested structure between the Instructor and the Course entity

    // --cascade-- attribute define the degree of power of the relation between to entities ,Like ,if we remove an entity from database should the other entity be removed or not.


    // similarly
    //@ManyToMany annotation links this entity which is (COURSE) with the entity defined in down field in this situation 'List<Student>' in the database
    // -- so we have MANY of COURSES linked to MANY of STUDENTS as 'List<Student>'.
    @ManyToMany
    @ToString.Exclude
    private List<Student> studentList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Course course = (Course) o;
        return id != 0 && Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
