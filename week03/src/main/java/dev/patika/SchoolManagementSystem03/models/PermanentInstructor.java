package dev.patika.SchoolManagementSystem03.models;

import javax.persistence.Entity;
import java.util.List;
import java.util.Objects;
@Entity
public class PermanentInstructor extends Instructor{
    int fixed_salary;

    public PermanentInstructor(String name, String address, long phoneNumber, List<Course> courseList, int fixed_salary) {
        super(name, address, phoneNumber, courseList);
        this.fixed_salary = fixed_salary;
    }
    public PermanentInstructor(String name, String address, long phoneNumber, int fixed_salary) {
        super(name, address, phoneNumber);
        this.fixed_salary = fixed_salary;
    }
    public PermanentInstructor() {}


    public int getFixed_salary() {
        return fixed_salary;
    }

    public void setFixed_salary(int fixed_salary) {
        this.fixed_salary = fixed_salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PermanentInstructor)) return false;
        if (!super.equals(o)) return false;
        PermanentInstructor that = (PermanentInstructor) o;
        return fixed_salary == that.fixed_salary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fixed_salary);
    }

    @Override
    public String toString() {
        return "SchoolManagementSystem.PermanentInstructor{" +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                "fixed_salary=" + fixed_salary +
                '}';
    }
}
