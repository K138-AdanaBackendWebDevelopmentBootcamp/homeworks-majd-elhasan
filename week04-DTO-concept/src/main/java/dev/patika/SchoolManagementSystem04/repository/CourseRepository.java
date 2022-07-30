package dev.patika.SchoolManagementSystem04.repository;

import dev.patika.SchoolManagementSystem04.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    void deleteByCourseCode(String Code);
    Course updateByCourseCode(Course course, String code);

    @Query("from Course c WHERE c.id=?1")
    Course findById(int id);
}
