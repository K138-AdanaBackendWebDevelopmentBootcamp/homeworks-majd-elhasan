//--------------------------------------------------------------
// (1) the Request journey start from here -controller layer-  ↓
//--------------------------------------------------------------
// -2- and then It's gonna to go to the -service layer-
// -3- and then It's gonna to go to DAO layer
// -4- over there It will be handled by entity manager which is controlled by Spring boot.
package dev.patika.SchoolManagementSystem03.controllers;

import dev.patika.SchoolManagementSystem03.controllers.Interface.IBaseController;
import dev.patika.SchoolManagementSystem03.models.Student;
import dev.patika.SchoolManagementSystem03.services.Interfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StudentController implements IBaseController<Student> {
    private final IStudentService<Student> iStudentService;
    // we make an instance of IStudentService<Student> to have it injected to this object by the constructor (constructor injection) ---HERE WE ARE TALKING ABOUT THE "DI" DEPENDENCY INJECTION---
    // DI types: Dependency Injection types
    // field injection
    // setter injection
    // constructor injection
    @Autowired
    public StudentController(IStudentService<Student> iStudentService){
        this.iStudentService = iStudentService;
    }

    @Override
    @GetMapping("/students")
    @Transactional(readOnly = true)
    public ResponseEntity<List<Student>> getAll() {
        return new ResponseEntity<>(iStudentService.findAll(),HttpStatus.OK);
    }

    @Override
    @GetMapping("/students/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<Student> getById(@PathVariable int id) {
        return new ResponseEntity<>(iStudentService.findById(id), HttpStatus.OK);
    }

    @Override
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateById(@RequestBody Student student,@PathVariable int id) {
        return new ResponseEntity<>(iStudentService.update(student,id),HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        iStudentService.deleteById(id);
        return new ResponseEntity<>("the student with id : "+id+" is deleted successfully",HttpStatus.OK);
    }

    @Override
    @PostMapping("/students")
    public ResponseEntity<Student> post(@RequestBody Student student) {
        return new ResponseEntity<>(iStudentService.save(student),HttpStatus.OK);
    }

}
