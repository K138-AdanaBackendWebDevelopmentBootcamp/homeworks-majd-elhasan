// -1- the Request journey start from here -controller layer-
//--------------------------------------------------------------
// (2) and then It's gonna to go to the -service layer-     ↓
//--------------------------------------------------------------
// -3- and then It's gonna to go to DAO layer
// -4- over there It will be handled by entity manager which is controlled by Spring boot.
package dev.patika.SchoolManagementSystem03.services;

import dev.patika.SchoolManagementSystem03.DAO.Interfaces.IInstructorDAO;
import dev.patika.SchoolManagementSystem03.models.PermanentInstructor;
import dev.patika.SchoolManagementSystem03.services.Interfaces.IInstructorService;
import org.springframework.stereotype.Service;

import java.util.List;
// We mark beans with @Service to indicate that they're holding the business logic. Besides, being used in the service layer, there Isn't any other special use for this annotation.
@Service
public class Permanent_InstructorServiceImpl implements IInstructorService<PermanentInstructor> {
    private final IInstructorDAO<PermanentInstructor> i_instructorDAO;
    // we make an instance of IInstructorDAO<PermanentInstructor> to have it injected to this object by the constructor (constructor injection) ---HERE WE ARE TALKING ABOUT THE "DI" DEPENDENCY INJECTION---
    // DI types: Dependency Injection types
    // field injection
    // setter injection
    // constructor injection
    public Permanent_InstructorServiceImpl(IInstructorDAO<PermanentInstructor> i_instructorDAO) {
        this.i_instructorDAO = i_instructorDAO;
    }

    @Override
    public PermanentInstructor findByPhoneNumber(Long phone_number) {
        return i_instructorDAO.findByPhoneNumber(phone_number);
    }

    @Override
    public void deleteByPhoneNumber(Long phone_number) {
        i_instructorDAO.deleteByPhoneNumber(phone_number);
    }

    @Override
    public PermanentInstructor updateByPhoneNumber(PermanentInstructor instructor, Long phone_number) {
        return i_instructorDAO.updateByPhoneNumber(instructor,phone_number);
    }

    @Override
    public List<PermanentInstructor> findAll() {
        return i_instructorDAO.findAll();
    }

    @Override
    public PermanentInstructor findById(int id) {
        return i_instructorDAO.findById(id);
    }

    @Override
    public PermanentInstructor save(PermanentInstructor instructor) {
        return i_instructorDAO.save(instructor);
    }

    @Override
    public void deleteById(int id) {
        i_instructorDAO.deleteById(id);
    }

    @Override
    public PermanentInstructor update(PermanentInstructor instructor, int id) {
        return i_instructorDAO.update(instructor,id);
    }
}
