package dev.patika.SchoolManagementSystem04.DTO;

import dev.patika.SchoolManagementSystem04.models.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String name ;
    private LocalDate birth_date;
    private String address;
    private String gender;
    private List<Course> courses;
}
