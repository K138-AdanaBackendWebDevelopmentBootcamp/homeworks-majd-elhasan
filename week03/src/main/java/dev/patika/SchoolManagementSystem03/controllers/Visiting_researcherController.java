//--------------------------------------------------------------
// (1) the Request journey start from here -controller layer-  ↓
//--------------------------------------------------------------
// -2- and then It's gonna to go to the -service layer-
// -3- and then It's gonna to go to DAO layer
// -4- over there It will be handled by entity manager which is controlled by Spring boot.
package dev.patika.SchoolManagementSystem03.controllers;

import dev.patika.SchoolManagementSystem03.controllers.Interface.IInstructorController;
import dev.patika.SchoolManagementSystem03.models.VisitingResearcher;
import dev.patika.SchoolManagementSystem03.services.Interfaces.IInstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@RestController annotation
// Spring 4.0 introduced the @RestController annotation in order to simplify the creation of RESTful web services.
// It's a convenient annotation that combines @Controller and @ResponseBody,
// which eliminates the need to annotate every request handling method of the controller class with the @ResponseBody annotation.
@RestController
public class Visiting_researcherController implements IInstructorController<VisitingResearcher> {

    private final IInstructorService<VisitingResearcher> i_researcherService;
    // we make an instance of IInstructorService<VisitingResearcher> to have it injected to this object by the constructor (constructor injection) ---HERE WE ARE TALKING ABOUT THE "DI" DEPENDENCY INJECTION---
    // DI types: Dependency Injection types
    // field injection
    // setter injection
    // constructor injection
    public Visiting_researcherController(IInstructorService<VisitingResearcher> i_researcherService) {
        this.i_researcherService = i_researcherService;
    }

    @Override
    @GetMapping("/visiting_researchers")
    @Transactional(readOnly = true)
    public ResponseEntity<List<VisitingResearcher>> getAll() {
        return new ResponseEntity<>(i_researcherService.findAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/visiting_researchers/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<VisitingResearcher> getById(@PathVariable int id) {
        return new ResponseEntity<>(i_researcherService.findById(id),HttpStatus.OK);
    }
    @GetMapping("/visiting_researchers/{phone}")
    @Transactional(readOnly = true)
    public ResponseEntity<VisitingResearcher> getByPhoneNumber(@PathVariable long phone) {
        return new ResponseEntity<>(i_researcherService.findByPhoneNumber(phone),HttpStatus.OK);
    }

    @Override
    @PutMapping("/visiting_researchers/{id}")
    @Transactional
    public ResponseEntity<VisitingResearcher> updateById(VisitingResearcher instructor,@PathVariable int id) {
        return new ResponseEntity<>(i_researcherService.update(instructor,id),HttpStatus.OK);
    }

    @PutMapping("/visiting_researchers/{phone}")
    @Transactional
    public ResponseEntity<VisitingResearcher> updateByPhoneNumber(VisitingResearcher instructor,@PathVariable long phone) {
        return new ResponseEntity<>(i_researcherService.updateByPhoneNumber(instructor,phone),HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/visiting_researchers/{id}")
    @Transactional
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        i_researcherService.deleteById(id);
        return ResponseEntity.ok("the visiting researcher with id :"+id+" is deleted");
    }
    @DeleteMapping("/visiting_researchers/{phone}")
    @Transactional
    public ResponseEntity<String> deleteByPhoneNumber(@PathVariable long phone) {
        i_researcherService.deleteByPhoneNumber(phone);
        return ResponseEntity.ok("the visiting researcher with phone number :"+phone+" is deleted");
    }

    @Override
    @PostMapping("/visiting_researchers")
    @Transactional
    public ResponseEntity<VisitingResearcher> post(VisitingResearcher instructor) {
        return ResponseEntity.ok(i_researcherService.save(instructor));
    }
}
