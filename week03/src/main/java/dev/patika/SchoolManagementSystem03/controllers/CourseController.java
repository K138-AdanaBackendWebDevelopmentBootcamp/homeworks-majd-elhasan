//--------------------------------------------------------------
// (1) the Request journey start from here -controller layer-  ↓
//--------------------------------------------------------------
// -2- and then It's gonna to go to the -service layer-
// -3- and then It's gonna to go to DAO layer
// -4- over there It will be handled by entity manager which is controlled by Spring boot.
package dev.patika.SchoolManagementSystem03.controllers;

import dev.patika.SchoolManagementSystem03.controllers.Interface.IBaseController;
import dev.patika.SchoolManagementSystem03.models.Course;
import dev.patika.SchoolManagementSystem03.services.Interfaces.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@RestController annotation
// Spring 4.0 introduced the @RestController annotation in order to simplify the creation of RESTful web services. [REST : representational state transfer]
// It's a convenient annotation that combines @Controller and @ResponseBody,
// which eliminates the need to annotate every request handling method of the controller class with the @ResponseBody annotation.
@RestController
public class CourseController implements IBaseController<Course> {
    private final ICourseService<Course> iCourseService;
    // we make an instance of ICourseService<Course> to have it injected to this object by the constructor (constructor injection) ---HERE WE ARE TALKING ABOUT THE "DI" DEPENDENCY INJECTION---
    // DI types: Dependency Injection types
    // field injection
    // setter injection
    // constructor injection
    @Autowired
    public CourseController(ICourseService<Course> iCourseService){
        this.iCourseService = iCourseService;
    }

    @Override
    @GetMapping("/courses")
    @Transactional(readOnly = true)
    public ResponseEntity<List<Course>> getAll() {
        return new ResponseEntity<>(iCourseService.findAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/courses/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<Course> getById(@PathVariable int id) {
        return new ResponseEntity<>(iCourseService.findById(id),HttpStatus.OK);
    }
    // when our path is like this ("/tasks/{parameter}") we should use @PathVariable annotation in front of our parameter, USAGE ---[course/2]--- '2' is the number as a parameter
    // if it was like this ("/tasks") we should use @RequestParam annotation in front of our parameter  ,USAGE ---[course?id=2]---where 2 is the number as a parameter
    @Override
    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateById(@RequestBody Course course,@PathVariable int id) {
        return new ResponseEntity<>(iCourseService.update(course,id),HttpStatus.OK);
    }
    @PutMapping("/courses/{code}")
    public ResponseEntity<Course> updateByCode(@PathVariable String code , @RequestBody Course course){
        return new ResponseEntity<>(iCourseService.updateByCode(course,code),HttpStatus.OK);
    }


    @Override
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        iCourseService.deleteById(id);
        return new ResponseEntity<>("the course with id : "+id+" is deleted successfully",HttpStatus.OK);
    }
    @DeleteMapping("courses/{code}")
    public ResponseEntity<String> deleteByCode(@PathVariable String code){
        iCourseService.deleteByCode(code);
        return new ResponseEntity<>("the course with Code : "+code+" is deleted successfully",HttpStatus.OK);
    }

    @Override
    @PostMapping("/courses")
    public ResponseEntity<Course> post(@RequestBody Course course) {
        return new ResponseEntity<>(iCourseService.save(course),HttpStatus.OK);
    }

}
