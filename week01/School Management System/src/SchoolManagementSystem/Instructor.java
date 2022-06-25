package SchoolManagementSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Instructor {
    String name;
    String address;
    long phoneNumber;
// an SchoolManagementSystem.Instructor should instruct at least one or more courses
    List<Course> courseList = new ArrayList<>();

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
            course.instructor = this;
        }
    }
    public void setCourseList(List<Course> courseList) {
        if(courseList.size()>0) this.courseList = courseList; // courseList contains at least one course
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
