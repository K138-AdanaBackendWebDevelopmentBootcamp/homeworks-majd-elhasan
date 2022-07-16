package dev.patika.SchoolManagementSystem03.services.Interfaces;

public interface IInstructorService<Instructor> extends IBaseService<Instructor>{
    Instructor findByPhoneNumber(Long phone_number);
    void deleteByPhoneNumber(Long phone_number);
    Instructor updateByPhoneNumber(Instructor instructor, Long phone_number);
}
