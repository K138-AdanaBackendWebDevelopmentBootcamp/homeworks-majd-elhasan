package dev.patika.schoolmanagementsystem.controllers;

import dev.patika.schoolmanagementsystem.controllers.Interface.IBaseController;
import dev.patika.schoolmanagementsystem.models.Instructor;
import dev.patika.schoolmanagementsystem.services.Interfaces.IInstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InstructorController implements IBaseController<Instructor> {
    private final IInstructorService<Instructor> iInstructorService;

    public InstructorController(IInstructorService<Instructor> iInstructorService){
        this.iInstructorService = iInstructorService;
    }

    @Override
    @GetMapping("/Instructors")
    @Transactional(readOnly = true)
    public ResponseEntity<List<Instructor>> getAll() {
        return new ResponseEntity<>(iInstructorService.findAll(), HttpStatus.OK);    }

    @Override
    @GetMapping("/Instructors/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<Instructor> get(@PathVariable int id) {
        return new ResponseEntity<>(iInstructorService.findById(id),HttpStatus.OK);
    }

    @Override
    @PutMapping("/Instructors/{id}")
    public ResponseEntity<Instructor> update(@PathVariable int id,@RequestBody Instructor instructor) {
        return new ResponseEntity<>(iInstructorService.update(instructor,id),HttpStatus.OK);
    }
    @PutMapping("/Instructors/{phone_number}")
    public ResponseEntity<Instructor> update(@PathVariable long phone_number,@RequestBody Instructor instructor) {
        return new ResponseEntity<>(iInstructorService.updateByPhoneNumber(instructor,phone_number),HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/Instructors/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        iInstructorService.deleteById(id);
        return new ResponseEntity<>("the instructor with id : "+id+" is deleted successfully",HttpStatus.OK);
    }
    @DeleteMapping("/Instructors/{phone_number}")
    public ResponseEntity<String> deleteByPhoneNumber(@PathVariable Long phone_number){
        iInstructorService.deleteByPhoneNumber(phone_number);
        return new ResponseEntity<>("the instructor with the phone_number "+phone_number+" is deleted successfully",HttpStatus.OK);
    }

    @Override
    @PostMapping("/Instructors")
    public ResponseEntity<Instructor> post(Instructor instructor) {
        return new ResponseEntity<>(iInstructorService.save(instructor),HttpStatus.OK);
    }
}
