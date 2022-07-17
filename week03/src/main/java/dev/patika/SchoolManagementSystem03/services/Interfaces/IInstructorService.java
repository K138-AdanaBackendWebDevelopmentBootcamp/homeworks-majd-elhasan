package dev.patika.SchoolManagementSystem03.services.Interfaces;
// this interface just conjunction between IBaseService and InstructorService_implementation classes
// this interface contains the common used methods in the inheritor --Instructor-- service (sub) classes as well.
public interface IInstructorService<Instructor> extends IBaseService<Instructor>{
    Instructor findByPhoneNumber(Long phone_number);
    void deleteByPhoneNumber(Long phone_number);
    Instructor updateByPhoneNumber(Instructor instructor, Long phone_number);
}
