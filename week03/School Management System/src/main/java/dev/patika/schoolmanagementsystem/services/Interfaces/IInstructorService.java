package dev.patika.schoolmanagementsystem.services.Interfaces;

public interface IInstructorService<Instructor> extends IBaseService<Instructor>{
    void deleteByPhoneNumber(Long phone_number);
    Instructor updateByPhoneNumber(Instructor instructor, Long phone_number);
}
