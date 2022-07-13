package dev.patika.schoolmanagementsystem.models;

import javax.persistence.Entity;
import java.util.List;
import java.util.Objects;
@Entity
public class VisitingResearcher extends Instructor{
    int hourly_salary;


    public VisitingResearcher(String name, String address, long phoneNumber, List<Course> courseList, int hourly_salary) {
        super(name, address, phoneNumber, courseList);
        this.hourly_salary = hourly_salary;
    }

    public VisitingResearcher() {

    }

    public int getHourly_salary() {
        return hourly_salary;
    }

    public void setHourly_salary(int hourly_salary) {
        this.hourly_salary = hourly_salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitingResearcher)) return false;
        if (!super.equals(o)) return false;
        VisitingResearcher that = (VisitingResearcher) o;
        return hourly_salary == that.hourly_salary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hourly_salary);
    }

    @Override
    public String toString() {
        return "SchoolManagementSystem.VisitingResearcher{" +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                "hourly_salary=" + hourly_salary +
                '}';
    }
}
