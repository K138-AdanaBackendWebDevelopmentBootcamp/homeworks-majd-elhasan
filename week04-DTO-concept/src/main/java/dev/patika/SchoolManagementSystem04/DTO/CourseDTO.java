package dev.patika.SchoolManagementSystem04.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private String courseName;
    private String courseCode;
    private int creditScore;
    private long instructor_id;
    private List<Long> students_id;

}
