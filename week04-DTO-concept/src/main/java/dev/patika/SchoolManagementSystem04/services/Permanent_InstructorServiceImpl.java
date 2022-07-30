// -1- the Request journey start from here -controller layer-
//--------------------------------------------------------------
// (2) and then It's gonna to go to the -service layer-     ↓
//--------------------------------------------------------------
// -3- and then It's gonna to go to DAO layer
// -4- over there It will be handled by entity manager which is controlled by Spring boot.
package dev.patika.SchoolManagementSystem04.services;

import dev.patika.SchoolManagementSystem04.models.Instructor;
import dev.patika.SchoolManagementSystem04.models.PermanentInstructor;
import dev.patika.SchoolManagementSystem04.repository.InstructorRepository;
import dev.patika.SchoolManagementSystem04.services.Interfaces.IInstructorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
// We mark beans with @Service to indicate that they're holding the business logic. Besides, being used in the service layer, there Isn't any other special use for this annotation.
@Service
public class Permanent_InstructorServiceImpl implements IInstructorService<PermanentInstructor> {
    private final InstructorRepository instructorRepository;
    // we make an instance of IInstructorDAO<PermanentInstructor> to have it injected to this object by the constructor (constructor injection) ---HERE WE ARE TALKING ABOUT THE "DI" DEPENDENCY INJECTION---
    // DI types: Dependency Injection types
    // field injection
    // setter injection
    // constructor injection
    public Permanent_InstructorServiceImpl(InstructorRepository instructorRepository){
        this.instructorRepository = instructorRepository;
    }

    @Override
    public PermanentInstructor findByPhoneNumber(Long phone_number) {
        return (PermanentInstructor) instructorRepository.findByPhoneNumber(phone_number);
    }

    @Override
    public void deleteByPhoneNumber(Long phone_number) {
        instructorRepository.deleteByPhoneNumber(phone_number);
    }

    @Override
    public PermanentInstructor updateByPhoneNumber(PermanentInstructor instructor) {
        return (PermanentInstructor)instructorRepository.updateByPhoneNumber(instructor);
    }

    @Override
    public List<PermanentInstructor> findAll() {
         List<Instructor> instructors = instructorRepository.findAll();
         List<PermanentInstructor> permanentInstructors = new ArrayList<>();
         for(Instructor i :instructors){
             if(i instanceof PermanentInstructor )
                permanentInstructors.add( (PermanentInstructor) i);
         }
        return permanentInstructors;
    }

    @Override
    public PermanentInstructor findById(int id) {
        return (PermanentInstructor) instructorRepository.findById(id).orElse(null);
    }

    @Override
    public PermanentInstructor save(PermanentInstructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public void deleteById(int id) {
        instructorRepository.deleteById(id);
    }
    
}
