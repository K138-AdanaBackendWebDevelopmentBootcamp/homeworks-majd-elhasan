package dev.patika.SchoolManagementSystem04.controllers.Interface;

import org.springframework.http.ResponseEntity;
// this interface contains the common used methods in the inheritor --Instructor-- controller (sub) classes.
// we added extra methods here to use them in subclasses in common.
public interface IInstructorController<Instructor> extends IBaseController<Instructor>{
    ResponseEntity<Instructor> getByPhoneNumber(long phone);
    ResponseEntity<String> deleteByPhoneNumber(long phone);
    ResponseEntity<Instructor> updateByPhoneNumber(Instructor instructor,long phone);
}
