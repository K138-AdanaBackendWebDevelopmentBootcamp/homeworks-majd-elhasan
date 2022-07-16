package dev.patika.SchoolManagementSystem03.DAO;

import dev.patika.SchoolManagementSystem03.DAO.Interfaces.IInstructorDAO;
import dev.patika.SchoolManagementSystem03.Exceptions.AlreadyExistsException;
import dev.patika.SchoolManagementSystem03.models.PermanentInstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
@Repository
public class Permanent_InstructorDAP_JPA_Impl implements IInstructorDAO<PermanentInstructor> {
    private final EntityManager entityManager;

    public Permanent_InstructorDAP_JPA_Impl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public PermanentInstructor findByPhoneNumber(Long phone_number) {
       return entityManager.createQuery("From PermanentInstructor i Where i.phoneNumber=:phoneParam",PermanentInstructor.class).setParameter("phoneParam",phone_number).getSingleResult();
    }

    @Override
    public void deleteByPhoneNumber(Long phone_number) {
        PermanentInstructor foundOne = entityManager.createQuery("From PermanentInstructor i Where i.phoneNumber=:phoneParam",PermanentInstructor.class).setParameter("phoneParam",phone_number).getSingleResult();
        if (foundOne!=null)
            entityManager.remove(foundOne);
    }

    @Override
    public PermanentInstructor updateByPhoneNumber(PermanentInstructor instructor, Long phone_number) {
        PermanentInstructor foundOne = entityManager.createQuery("From PermanentInstructor i Where i.phoneNumber=:phoneParam",PermanentInstructor.class).setParameter("phoneParam",phone_number).getSingleResult();
        foundOne.setName(instructor.getName());
        foundOne.setAddress(instructor.getAddress());
        foundOne.setCourseList(instructor.getCourseList());
        foundOne.setFixed_salary(instructor.getFixed_salary());
        return entityManager.merge(foundOne);
    }

    @Override
    public List<PermanentInstructor> findAll() {
        return entityManager.createQuery("From PermanentInstructor ",PermanentInstructor.class).getResultList();
    }

    @Override
    public PermanentInstructor findById(int id) {
        return entityManager.createQuery("From PermanentInstructor i Where i.id=:idParam",PermanentInstructor.class).setParameter("idParam",id).getSingleResult();
    }

    @Override
    public PermanentInstructor save(PermanentInstructor instructor) {
        checkingObjectExistence(instructor);
        return entityManager.merge(instructor);
    }

    @Override
    public void deleteById(int id) {
        PermanentInstructor foundOne = entityManager.createQuery("From PermanentInstructor i Where i.id=:idParam",PermanentInstructor.class).setParameter("idParam",id).getSingleResult();
        if (foundOne!=null)
            entityManager.remove(foundOne);
    }

    @Override
    public PermanentInstructor update(PermanentInstructor instructor, int id) {
        PermanentInstructor foundOne = entityManager.createQuery("From PermanentInstructor i Where i.id=:idParam",PermanentInstructor.class).setParameter("idParam",id).getSingleResult();
        foundOne.setName(instructor.getName());
        foundOne.setAddress(instructor.getAddress());
        foundOne.setPhoneNumber(instructor.getPhoneNumber());
        foundOne.setCourseList(instructor.getCourseList());
        foundOne.setFixed_salary(instructor.getFixed_salary());
        return entityManager.merge(foundOne);
    }

    private void checkingObjectExistence(PermanentInstructor instructor){
        List<PermanentInstructor> foundInstructors = entityManager.createQuery("FROM PermanentInstructor i WHERE i.phoneNumber=:phoneParam ",PermanentInstructor.class)
                .setParameter("phoneParam",instructor.getPhoneNumber()).getResultList();
        if(foundInstructors.size() > 0)
            throw new AlreadyExistsException(""+instructor+"\n"+"this permanent instructor already exists !");
    }
}
