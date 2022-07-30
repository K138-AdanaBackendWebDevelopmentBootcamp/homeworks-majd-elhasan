package dev.patika.SchoolManagementSystem04.DTO;

import dev.patika.SchoolManagementSystem04.models.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Visiting_InstructorDTO {
    private String name;
    private String address;
    private long phoneNumber;
    private int hourly_salary;
    private List<Student> students;
}
