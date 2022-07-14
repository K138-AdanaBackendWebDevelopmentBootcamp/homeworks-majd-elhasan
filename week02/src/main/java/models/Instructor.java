package models;

import javax.persistence.*;
import java.util.*;

@Inheritance(strategy = InheritanceType.JOINED) // cause this class is a super class we should annotate it with @Inheritance  // ,We chose the Inheritance type as JOINED to make the classes (or tabled linked with each other)
@Entity   // annotated by Entity for connect the class with DB
public class Instructor {  // I'm going to make this class abstract
    @Id    // the primary key of this object(raw) of the class(table)  // (DB keyword)
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // to generate anh id automatically added this annotation
    //@Column(name = "instructor_id")
    private int id;
    String name;
    String address;
    long phoneNumber;
    // an SchoolManagementSystem.Instructor should instruct at least one or more courses
    @OneToMany(mappedBy = "instructor")
    List<Course> courseList = new ArrayList<>();

    public Instructor(){}

    public Instructor(String name, String address, long phoneNumber, List<Course> courseList) {
        if(courseList.size()>0) {
            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.courseList = courseList;
            Course[] inCourseList= new Course[courseList.size()];
            setCourseInstructor(courseList.toArray(inCourseList));
        }
    }
    public Instructor(String name, String address, long phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
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
        return this.courseList;
    }

    public int getId() {
        return id;
    }

    private void setCourseInstructor(Course... courseList){ // to set an instructor to each course in the list
        for (Course course: courseList) {
            course.setInstructor(this);
        }
    }
    public void setCourse(Course... courses) { // course... courses pattern allows us to handle zero or a lot of courses as if we type Course[] courses
        //this.courseList.addAll(Arrays.asList(courses));
        //or we can use Collections.addAll(this.courseList,courses);
        // or for loop to do that
        // But we have to check whither the Instructor have a specific course or not,to keep it not duplicated
        outer:
        for (Course course : courses) {
            for (Course value : this.courseList) {
                if (Objects.equals(course.getCourseCode(), value.getCourseCode()))
                    continue outer;
            }
            this.courseList.add(course);
            course.setInstructor(this);
        }
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
        Course[] inCourseList= new Course[courseList.size()];
        setCourseInstructor(courseList.toArray(inCourseList));
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

