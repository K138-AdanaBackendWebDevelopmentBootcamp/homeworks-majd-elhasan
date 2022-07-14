package dev.patika.SchoolManagementSystem03.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
// should be abstract
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String name;
    String address;
    long phoneNumber;
    // an Instructor should instruct at least one or more courses
    @OneToMany(mappedBy = "instructor")
    List<Course> courseList = new ArrayList<>();

    public int getId() {
        return id;
    }
    public Instructor(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    private void setCourseInstructor(List<Course> courseList){ // to set an instructor to each course in the list
        for (Course course: courseList) {
            course.setInstructor(this);
        }
    }
    public void setCourse(Course... courses) { // course... courses pattern allows us to handle zero or a lot of courses as if we type Course[] courses
        //this.courseList.addAll(Arrays.asList(courses));
        //or we can use Collections.addAll(this.courseList,courses);
        //or just in normal for loop
        outer:
        for (Course course : courses) {
            for (Course value : this.courseList) {
                if (Objects.equals(course.getCourseCode(), value.getCourseCode()))
                    continue outer;
            }
            this.courseList.add(course);
        }
        setCourseInstructor(Arrays.asList(courses));
    }
    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList; // courseList contains at least one course
        setCourseInstructor(courseList);
    }

    public Instructor(String name, String address, long phoneNumber, List<Course> courseList) {
        if(courseList.size()>0) {
            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.courseList = courseList;
            setCourseInstructor(courseList);
        }
    }
    public Instructor(String name, String address, long phoneNumber) {
            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instructor)) return false;
        Instructor that = (Instructor) o;
        return phoneNumber == that.phoneNumber && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,  phoneNumber);
    }

    @Override
    public String toString() {
        return "SchoolManagementSystem.Instructor{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
