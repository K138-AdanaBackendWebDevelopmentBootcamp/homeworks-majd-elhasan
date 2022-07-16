package dev.patika.SchoolManagementSystem03.services;

import dev.patika.SchoolManagementSystem03.DAO.Interfaces.IInstructorDAO;
import dev.patika.SchoolManagementSystem03.models.VisitingResearcher;
import dev.patika.SchoolManagementSystem03.services.Interfaces.IInstructorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Visiting_researcherServiceImpl implements IInstructorService<VisitingResearcher> {
    private final IInstructorDAO<VisitingResearcher> iresearcherDAO;

    public Visiting_researcherServiceImpl(IInstructorDAO<VisitingResearcher> iresearcherDAO) {
        this.iresearcherDAO = iresearcherDAO;
    }

    @Override
    public VisitingResearcher findByPhoneNumber(Long phone_number) {
        return iresearcherDAO.findByPhoneNumber(phone_number);
    }

    @Override
    public void deleteByPhoneNumber(Long phone_number) {
        iresearcherDAO.deleteByPhoneNumber(phone_number);
    }

    @Override
    public VisitingResearcher updateByPhoneNumber(VisitingResearcher visitingResearcher, Long phone_number) {
        return iresearcherDAO.updateByPhoneNumber(visitingResearcher,phone_number);
    }

    @Override
    public List<VisitingResearcher> findAll() {
        return iresearcherDAO.findAll();
    }

    @Override
    public VisitingResearcher findById(int id) {
        return iresearcherDAO.findById(id);
    }

    @Override
    public VisitingResearcher save(VisitingResearcher instructor) {
        return iresearcherDAO.save(instructor);
    }

    @Override
    public void deleteById(int id) {
        iresearcherDAO.deleteById(id);
    }

    @Override
    public VisitingResearcher update(VisitingResearcher instructor, int id) {
        return iresearcherDAO.update(instructor,id);
    }
}
