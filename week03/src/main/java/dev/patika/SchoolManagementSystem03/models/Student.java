package dev.patika.SchoolManagementSystem03.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
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
    private List<Course> CourseList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public Student() {}

    public Student(String name, LocalDate birth_date, String address, String gender) {
        this.name = name;
        this.birth_date = birth_date;
        this.address = address;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Course> getCourseList() {
        return CourseList;
    }

    public void setCourseList(List<Course> courseList) {
        CourseList = courseList;
    }


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

    @Override
    public String toString() {
        return "SchoolManagementSystem.Student{" +
                "name='" + name + '\'' +
                ", birth_date=" + birth_date +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
