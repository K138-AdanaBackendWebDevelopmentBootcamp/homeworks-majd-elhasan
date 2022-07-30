package dev.patika.SchoolManagementSystem04.repository;

import dev.patika.SchoolManagementSystem04.models.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor,Integer> {

    Instructor findByPhoneNumber(Long phone_number);
    void deleteByPhoneNumber(Long phone_number);
    Instructor updateByPhoneNumber(Instructor instructor);

     List<Instructor> findAll();
}
