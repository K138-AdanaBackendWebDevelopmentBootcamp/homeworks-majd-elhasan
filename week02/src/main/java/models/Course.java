package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity   // annotated by Entity for connect the class with DB
public class Course {
    @Id    // the primary key of this object(raw) of the class(table)  // (DB keyword)
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // to generate anh id automatically added this annotation
    private int id;
    private String courseName;
    private String courseCode;
    private int creditScore;

    @ManyToMany
    private final List<Student> studentList= new ArrayList<>();  // we have to initialize it to add students later
    @ManyToOne
    private Instructor instructor;    //@JoinColumn(name = "instructor_id")  we can type anything instead of "instructor_id"


    public Course(String courseName, String courseCode, int creditScore, Instructor instructor) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.creditScore = creditScore;
        this.instructor = instructor;
    }
    public Course(String courseName, String courseCode, int creditScore) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.creditScore = creditScore;
    }

    public Course(){
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

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public int getId() {
        return id;
    }

    public List<Student> getStudentList() {
        return studentList;
    }
    public void setStudent(Student... students) {
        for(Student student:students) {
            this.studentList.add(student);
            student.getCourseList().add(this);
        }
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
        return Objects.equals(courseCode, course.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode);
    }
}

