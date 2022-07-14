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

@RestController
public class CourseController implements IBaseController<Course> {
    private final ICourseService<Course> iCourseService;

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
    public ResponseEntity<Course> get(@PathVariable int id) {
        return new ResponseEntity<>(iCourseService.findById(id),HttpStatus.OK);
    }

    @Override
    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> update(@PathVariable int id,@RequestBody Course course) {
        return new ResponseEntity<>(iCourseService.update(course,id),HttpStatus.OK);
    }
    @PutMapping("/courses/{code}")
    public ResponseEntity<Course> updateByCode(@PathVariable String code , @RequestBody Course course){
        return new ResponseEntity<>(iCourseService.updateByCode(course,code),HttpStatus.OK);
    }


    @Override
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
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
    public ResponseEntity<Course> post(Course course) {
        return new ResponseEntity<>(iCourseService.save(course),HttpStatus.OK);
    }

}
