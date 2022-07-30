// -1- the Request journey start from here -controller layer-
// -2- and then It's gonna to go to the -service layer-
//--------------------------------------------------------------
// (3) and then It's gonna to go to DAO layer               ↓
//--------------------------------------------------------------
// -4- over there It will be handled by entity manager which is controlled by Spring boot.
package dev.patika.SchoolManagementSystem04.DAO;

import dev.patika.SchoolManagementSystem04.DAO.Interfaces.IInstructorDAO;
import dev.patika.SchoolManagementSystem04.Exceptions.AlreadyExistsException;
import dev.patika.SchoolManagementSystem04.models.VisitingResearcher;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;
//@Repository’s job is to catch persistence-specific exceptions and re-throw them as one of Spring’s unified unchecked exceptions.
@Repository
public class Visiting_researcherDAO_JPA_Impl implements IInstructorDAO<VisitingResearcher> {
    private final EntityManager entityManager;
    // we make an instance of EntityManager to have it injected to this object by the constructor (constructor injection) ---HERE WE ARE TALKING ABOUT THE "DI" DEPENDENCY INJECTION---
    // DI types: Dependency Injection types
    // field injection
    // setter injection
    // constructor injection
    public Visiting_researcherDAO_JPA_Impl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public VisitingResearcher findByPhoneNumber(Long phone_number) {
        return entityManager.createQuery("From VisitingResearcher i Where i.phoneNumber=:phoneParam",VisitingResearcher.class).setParameter("phoneParam",phone_number).getSingleResult();
    }

    @Override
    public void deleteByPhoneNumber(Long phone_number) {
        VisitingResearcher foundOne = entityManager.createQuery("From VisitingResearcher i Where i.phoneNumber=:phoneParam",VisitingResearcher.class).setParameter("phoneParam",phone_number).getSingleResult();
        if (foundOne!=null)
            entityManager.remove(foundOne);
    }

    @Override
    public VisitingResearcher updateByPhoneNumber(VisitingResearcher visitingResearcher, Long phone_number) {
        VisitingResearcher foundOne = entityManager.createQuery("From VisitingResearcher i Where i.phoneNumber=:phoneParam",VisitingResearcher.class).setParameter("phoneParam",phone_number).getSingleResult();
        foundOne.setName(visitingResearcher.getName());
        foundOne.setAddress(visitingResearcher.getAddress());
        foundOne.setCourseList(visitingResearcher.getCourseList());
        foundOne.setHourly_salary(visitingResearcher.getHourly_salary());
        return entityManager.merge(foundOne);    }

    @Override
    public List<VisitingResearcher> findAll() {
        return entityManager.createQuery("From VisitingResearcher ",VisitingResearcher.class).getResultList();
    }

    @Override
    public VisitingResearcher findById(int id) {
        return entityManager.createQuery("From VisitingResearcher i Where i.id=:idParam",VisitingResearcher.class).setParameter("idParam",id).getSingleResult();
    }

    @Override
    public VisitingResearcher save(VisitingResearcher instructor) {
        checkingObjectExistence(instructor);
        return entityManager.merge(instructor);
    }

    @Override
    public void deleteById(int id) {
        VisitingResearcher foundOne = entityManager.createQuery("From VisitingResearcher i Where i.id=:idParam",VisitingResearcher.class).setParameter("idParam",id).getSingleResult();
        if (foundOne!=null)
            entityManager.remove(foundOne);
    }

    @Override
    public VisitingResearcher update(VisitingResearcher instructor, int id) {
        VisitingResearcher foundOne = entityManager.createQuery("From VisitingResearcher i Where i.id=:idParam",VisitingResearcher.class).setParameter("idParam",id).getSingleResult();
        foundOne.setName(instructor.getName());
        foundOne.setAddress(instructor.getAddress());
        foundOne.setPhoneNumber(instructor.getPhoneNumber());
        foundOne.setCourseList(instructor.getCourseList());
        foundOne.setHourly_salary(instructor.getHourly_salary());
        return entityManager.merge(foundOne);
    }

    private void checkingObjectExistence(VisitingResearcher instructor){
        List<VisitingResearcher> foundInstructors = entityManager.createQuery("FROM VisitingResearcher i WHERE i.phoneNumber=:phoneParam ",VisitingResearcher.class)
                .setParameter("phoneParam",instructor.getPhoneNumber()).getResultList();
        if(foundInstructors.size() > 0)
            throw new AlreadyExistsException(""+instructor+"\n"+"this visiting researcher already exists !");
    }
}
