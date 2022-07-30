// -1- the Request journey start from here -controller layer-
//--------------------------------------------------------------
// (2) and then It's gonna to go to the -service layer-     ↓
//--------------------------------------------------------------
// -3- and then It's gonna to go to DAO layer
// -4- over there It will be handled by entity manager which is controlled by Spring boot.
package dev.patika.SchoolManagementSystem04.services;

import dev.patika.SchoolManagementSystem04.models.Instructor;
import dev.patika.SchoolManagementSystem04.models.VisitingResearcher;
import dev.patika.SchoolManagementSystem04.repository.InstructorRepository;
import dev.patika.SchoolManagementSystem04.services.Interfaces.IInstructorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
// We mark beans with @Service to indicate that they're holding the business logic. Besides, being used in the service layer, there Isn't any other special use for this annotation.
@Service
public class Visiting_researcherServiceImpl implements IInstructorService<VisitingResearcher> {
    private final InstructorRepository instructorRepository;
    // we make an instance of IInstructorDAO<VisitingResearcher> to have it injected to this object by the constructor (constructor injection) ---HERE WE ARE TALKING ABOUT THE "DI" DEPENDENCY INJECTION---
    // DI types: Dependency Injection types
    // field injection
    // setter injection
    // constructor injection
    public Visiting_researcherServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public VisitingResearcher findByPhoneNumber(Long phone_number) {
        return (VisitingResearcher) instructorRepository.findByPhoneNumber(phone_number);
    }

    @Override
    public void deleteByPhoneNumber(Long phone_number) {
        instructorRepository.deleteByPhoneNumber(phone_number);
    }

    @Override
    public VisitingResearcher updateByPhoneNumber(VisitingResearcher visitingResearcher) {
        return (VisitingResearcher)instructorRepository.updateByPhoneNumber(visitingResearcher);
    }

    @Override
    public List<VisitingResearcher> findAll() {
        List<Instructor> instructors = instructorRepository.findAll();
        List<VisitingResearcher> visitingResearchers = new ArrayList<>();
        for(Instructor i :instructors){
            if(i instanceof VisitingResearcher )
                visitingResearchers.add( (VisitingResearcher) i);
        }
        return visitingResearchers;
    }

    @Override
    public VisitingResearcher findById(int id) {
        return (VisitingResearcher) instructorRepository.findById(id).orElse(null);
    }

    @Override
    public VisitingResearcher save(VisitingResearcher instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public void deleteById(int id) {
        instructorRepository.deleteById(id);
    }
    
}
