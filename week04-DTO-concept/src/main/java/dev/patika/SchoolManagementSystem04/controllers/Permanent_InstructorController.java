//--------------------------------------------------------------
// (1) the Request journey start from here -controller layer-  ↓
//--------------------------------------------------------------
// -2- and then It's gonna to go to the -service layer-
// -3- and then It's gonna to go to DAO layer
// -4- over there It will be handled by entity manager which is controlled by Spring boot.
package dev.patika.SchoolManagementSystem04.controllers;

import dev.patika.SchoolManagementSystem04.controllers.Interface.IInstructorController;
import dev.patika.SchoolManagementSystem04.models.PermanentInstructor;
import dev.patika.SchoolManagementSystem04.services.Interfaces.IInstructorService;
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
public class Permanent_InstructorController implements IInstructorController<PermanentInstructor> {

    private final IInstructorService<PermanentInstructor> i_instructorService;
    // we make an instance of IInstructorService<PermanentInstructor> to have it injected to this object by the constructor (constructor injection) ---HERE WE ARE TALKING ABOUT THE "DI" DEPENDENCY INJECTION---
    // DI types: Dependency Injection types
    // field injection
    // setter injection
    // constructor injection
    public Permanent_InstructorController(IInstructorService<PermanentInstructor> i_instructorService) {
        this.i_instructorService = i_instructorService;
    }

    @Override
    @GetMapping("/permanent_instructors")
    @Transactional(readOnly = true)
    public ResponseEntity<List<PermanentInstructor>> getAll() {
        return new ResponseEntity<>(i_instructorService.findAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/permanent_instructor/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<PermanentInstructor> getById(@PathVariable int id) {
        return new ResponseEntity<>(i_instructorService.findById(id),HttpStatus.OK);
    }
    @GetMapping("/permanent_instructor/{phone}")
    @Transactional(readOnly = true)
    public ResponseEntity<PermanentInstructor> getByPhoneNumber(@PathVariable long phone) {
        return new ResponseEntity<>(i_instructorService.findByPhoneNumber(phone),HttpStatus.OK);
    }

    @Override
    @PutMapping("/permanent_instructors/{id}")
    @Transactional
    public ResponseEntity<PermanentInstructor> updateById(PermanentInstructor instructor,@PathVariable int id) {
        return new ResponseEntity<>(i_instructorService.save(instructor),HttpStatus.OK);
    }
    @PutMapping("/permanent_instructors/{phone}")
    @Transactional
    public ResponseEntity<PermanentInstructor> updateByPhoneNumber(PermanentInstructor instructor,@PathVariable long phone) {
        return new ResponseEntity<>(i_instructorService.updateByPhoneNumber(instructor),HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/permanent_instructors/{id}")
    @Transactional
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        i_instructorService.deleteById(id);
        return ResponseEntity.ok("the permanent instructor with id :"+id+" is deleted");
    }
    @DeleteMapping("/permanent_instructors/{phone}")
    @Transactional
    public ResponseEntity<String> deleteByPhoneNumber(@PathVariable long phone) {
        i_instructorService.deleteByPhoneNumber(phone);
        return ResponseEntity.ok("the permanent instructor with phone number :"+phone+" is deleted");
    }

    @Override
    @PostMapping("/permanent_instructors")
    @Transactional
    public ResponseEntity<PermanentInstructor> post(PermanentInstructor instructor) {
        return ResponseEntity.ok(i_instructorService.save(instructor));
    }
}
