package models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity   // annotated by Entity for connect the class with DB
public class Student {
    @Id    // the primary key of this object(raw) of the class(table)  // (DB keyword)
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // to generate anh id automatically added this annotation
    private int id;
    private String name ;
    private LocalDate birth_date;
    private String address;
    private String gender;

    @ManyToMany
    private List<Course> courseList = new ArrayList<>();  // we have to initialize it to add courses later

    Student(){
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
        return courseList;
    }

    public void setCourse(Course... courses) {  // when we set a course to a student ,we add that student to the student list of this course
        for (Course course:courses) {
            courseList.add(course);
            course.studentList.add(this);
        }
    }

    public int getId() {
        return id;
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

    //@Override
    //public int hashCode() {return Objects.hash(name, birth_date, address, gender);}

    // cause I dont have something unique for Student I prefer not to use hashCode() method

    @Override
    public String toString() {
        return "SchoolManagementSystem.Student{" +
                "name='" + name + '\'' +
                ", birth_date=" + birth_date +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", course list='" + courseList + '\'' +           // for some reason courseList returns empty when we print it as a string !!
                '}';
    }
}
