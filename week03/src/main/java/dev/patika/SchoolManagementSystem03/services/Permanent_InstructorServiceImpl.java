package dev.patika.SchoolManagementSystem03.services;

import dev.patika.SchoolManagementSystem03.DAO.Interfaces.IInstructorDAO;
import dev.patika.SchoolManagementSystem03.models.PermanentInstructor;
import dev.patika.SchoolManagementSystem03.services.Interfaces.IInstructorService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Permanent_InstructorServiceImpl implements IInstructorService<PermanentInstructor> {
    private final IInstructorDAO<PermanentInstructor> iinstructorDAO;

    public Permanent_InstructorServiceImpl(IInstructorDAO<PermanentInstructor> iinstructorDAO) {
        this.iinstructorDAO = iinstructorDAO;
    }

    @Override
    public PermanentInstructor findByPhoneNumber(Long phone_number) {
        return iinstructorDAO.findByPhoneNumber(phone_number);
    }

    @Override
    public void deleteByPhoneNumber(Long phone_number) {
        iinstructorDAO.deleteByPhoneNumber(phone_number);
    }

    @Override
    public PermanentInstructor updateByPhoneNumber(PermanentInstructor instructor, Long phone_number) {
        return iinstructorDAO.updateByPhoneNumber(instructor,phone_number);
    }

    @Override
    public List<PermanentInstructor> findAll() {
        return iinstructorDAO.findAll();
    }

    @Override
    public PermanentInstructor findById(int id) {
        return iinstructorDAO.findById(id);
    }

    @Override
    public PermanentInstructor save(PermanentInstructor instructor) {
        return iinstructorDAO.save(instructor);
    }

    @Override
    public void deleteById(int id) {
        iinstructorDAO.deleteById(id);
    }

    @Override
    public PermanentInstructor update(PermanentInstructor instructor, int id) {
        return iinstructorDAO.update(instructor,id);
    }
}
