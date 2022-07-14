package dev.patika.SchoolManagementSystem03.services;

import dev.patika.SchoolManagementSystem03.DAO.Interfaces.IInstructorDAO;
import dev.patika.SchoolManagementSystem03.models.Instructor;
import dev.patika.SchoolManagementSystem03.services.Interfaces.IInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InstructorServiceImpl implements IInstructorService<Instructor> {

    private final IInstructorDAO<Instructor> iInstructorDAO;

    @Autowired
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
