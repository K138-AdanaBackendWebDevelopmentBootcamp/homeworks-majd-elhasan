package dev.patika.SchoolManagementSystem03.DAO.Interfaces;

public interface IInstructorDAO<Instructor> extends IBaseDAO<Instructor>{
    void deleteByPhoneNumber(Long phone_number);
    Instructor updateByPhoneNumber(Instructor instructor, Long phone_number);
}
