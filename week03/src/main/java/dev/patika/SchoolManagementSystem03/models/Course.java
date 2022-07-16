package dev.patika.SchoolManagementSystem03.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String courseName;
    private String courseCode;
    private int creditScore;

    @ManyToOne(cascade=CascadeType.ALL)
    //@JsonIgnore  // we can use JsonIgnore to not show up the instructor entity inside Course entity ,to avoid the StackOverFlow error whereas we have a nested structure between the Instructor and the Course entity
    // if we don't use @JsonIdentityInfo annotation
    private Instructor instructor;

    @ManyToMany

    private List<Student> studentList = new ArrayList<>();



    public Course (String courseName , String courseCode , int creditScore){
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.creditScore = creditScore;
    }

    public Course(String courseName, String courseCode, int creditScore, Instructor instructor) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.creditScore = creditScore;
        this.instructor = instructor;
    }

    public Course() {

    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Student> getStudentList() {
        return this.studentList;
    }

    public void setStudent(Student... students) {
        for(Student student:students) {
            this.studentList.add(student);
            student.getCourseList().add(this);
        }
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseName() {
        return courseName;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }
    public int getCreditScore() {
        return creditScore;
    }

    //@JsonIgnore
    public Instructor getInstructor() {
        //Instructor local_instructor=new Instructor();
        //List<Course> otherCourses = new ArrayList<>();
        //        for(Course course : this.instructor.courseList){
        //            if(!course.equals(this)) {
        //                //otherCourses.add(this);
        //            }
        //        }
        //local_instructor.setCourseList(null);
        //return local_instructor;
        return this.instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        String instructorName = instructor!=null? instructor.getName():"Not found";
        return "SchoolManagementSystem.Course{" +
                "courseName='" + courseName + '\'' +
                ", courseCode=" + courseCode +
                ", creditScore=" + creditScore +
                ", instructor=" +instructorName+
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return courseCode.equals(course.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode);
    }
}
