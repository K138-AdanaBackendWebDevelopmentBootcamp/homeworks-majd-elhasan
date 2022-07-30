package dev.patika.SchoolManagementSystem04.repository;

import dev.patika.SchoolManagementSystem04.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
