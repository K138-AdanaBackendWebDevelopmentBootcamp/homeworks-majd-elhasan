package dev.patika.schoolmanagementsystem.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name ;
    private LocalDate birth_date;
    private String address;
    private String gender;

    @ManyToMany(mappedBy = "studentList",cascade = CascadeType.ALL)
    private List<Course> CourseList = new ArrayList<>();

    public Integer getId() {
        return id;
    }


    public Student() {

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



    public Student(String name, LocalDate birth_date, String address, String gender) {
        this.name = name;
        this.birth_date = birth_date;
        this.address = address;
        this.gender = gender;
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
