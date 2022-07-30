// -1- the Request journey start from here -controller layer-
// -2- and then It's gonna to go to the -service layer-
//--------------------------------------------------------------
// (3) and then It's gonna to go to DAO layer               ↓
//--------------------------------------------------------------
// -4- over there It will be handled by entity manager which is controlled by Spring boot.
package dev.patika.SchoolManagementSystem04.DAO;

import dev.patika.SchoolManagementSystem04.DAO.Interfaces.IInstructorDAO;
import dev.patika.SchoolManagementSystem04.Exceptions.AlreadyExistsException;
import dev.patika.SchoolManagementSystem04.models.PermanentInstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
//@Repository’s job is to catch persistence-specific exceptions and re-throw them as one of Spring’s unified unchecked exceptions.
@Repository
public class Permanent_InstructorDAP_JPA_Impl implements IInstructorDAO<PermanentInstructor> {
    private final EntityManager entityManager;
    // we make an instance of EntityManager to have it injected to this object by the constructor (constructor injection) ---HERE WE ARE TALKING ABOUT THE "DI" DEPENDENCY INJECTION---
    // DI types: Dependency Injection types
    // field injection
    // setter injection
    // constructor injection
    public Permanent_InstructorDAP_JPA_Impl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public PermanentInstructor findByPhoneNumber(Long phone_number) {
       return entityManager.createQuery("From PermanentInstructor i Where i.phoneNumber=:phoneParam",PermanentInstructor.class).setParameter("phoneParam",phone_number).getSingleResult();
    }
    // in the method above set the course ID to idParam SYNTAX:" FROM Object o WHERE o.id=:SOMETHING_AS_PARAMETER" and then we call setParameter method inside it ("SOMETHING_AS_PARAMETER",id)
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
