package dev.patika.SchoolManagementSystem03.services.Interfaces;

public interface IInstructorService<Instructor> extends IBaseService<Instructor>{
    void deleteByPhoneNumber(Long phone_number);
    Instructor updateByPhoneNumber(Instructor instructor, Long phone_number);
}
