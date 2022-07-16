package dev.patika.SchoolManagementSystem03.controllers;

import dev.patika.SchoolManagementSystem03.controllers.Interface.IInstructorController;
import dev.patika.SchoolManagementSystem03.models.PermanentInstructor;
import dev.patika.SchoolManagementSystem03.services.Interfaces.IInstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class Permanent_InstructorController implements IInstructorController<PermanentInstructor> {

    private final IInstructorService<PermanentInstructor> i_instructorService;

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
        return new ResponseEntity<>(i_instructorService.update(instructor,id),HttpStatus.OK);
    }
    @PutMapping("/permanent_instructors/{phone}")
    @Transactional
    public ResponseEntity<PermanentInstructor> updateByPhoneNumber(PermanentInstructor instructor,@PathVariable long phone) {
        return new ResponseEntity<>(i_instructorService.updateByPhoneNumber(instructor,phone),HttpStatus.OK);
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
