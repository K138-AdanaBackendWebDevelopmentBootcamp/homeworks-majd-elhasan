package dev.patika.schoolmanagementsystem.services;

import dev.patika.schoolmanagementsystem.DAO.Interfaces.IInstructorDAO;
import dev.patika.schoolmanagementsystem.models.Instructor;
import dev.patika.schoolmanagementsystem.services.Interfaces.IInstructorService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InstructorServiceImpl implements IInstructorService<Instructor> {

    private final IInstructorDAO<Instructor> iInstructorDAO;

    public InstructorServiceImpl(IInstructorDAO<Instructor> iInstructorDAO) {
        this.iInstructorDAO = iInstructorDAO;
    }

    @Override
    public List<Instructor> findAll() {
        return iInstructorDAO.findAll();
    }

    @Override
    public Instructor findById(int id) {
        return iInstructorDAO.findById(id);
    }

    @Override
    public Instructor save(Instructor instructor) {
        return iInstructorDAO.save(instructor);
    }

    @Override
    public void deleteById(int id) {
        iInstructorDAO.deleteById(id);
    }

    @Override
    public void deleteByPhoneNumber(Long phone_number) {
            iInstructorDAO.deleteByPhoneNumber(phone_number);
    }

    @Override
    public Instructor update(Instructor instructor, int id) {
        return iInstructorDAO.update(instructor,id);
    }
    @Override
    public Instructor updateByPhoneNumber(Instructor instructor,Long phone_number) {
       return iInstructorDAO.updateByPhoneNumber(instructor, phone_number);
    }
}
