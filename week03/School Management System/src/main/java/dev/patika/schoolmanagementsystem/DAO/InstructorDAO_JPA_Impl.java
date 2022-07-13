package dev.patika.schoolmanagementsystem.DAO;

import dev.patika.schoolmanagementsystem.DAO.Interfaces.IInstructorDAO;
import dev.patika.schoolmanagementsystem.Exceptions.AlreadyExistsException;
import dev.patika.schoolmanagementsystem.models.Instructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

// I'm going to work on this class later...
@Repository
public class InstructorDAO_JPA_Impl implements IInstructorDAO<Instructor> {
    private final EntityManager entityManager;

    public InstructorDAO_JPA_Impl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)  // we use readOnly attribute to lower the load on the program and prevent saving processes from occurring.
    public List<Instructor> findAll() {
        return entityManager.createQuery("FROM Instructor ",Instructor.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Instructor findById(int id) {
        return (Instructor) entityManager.createQuery("FROM Instructor i WHERE i.id =:idParam")
                .setParameter("idParam",id).getSingleResult();
    }
    @Override
    public Instructor save(Instructor instructor) {
        checkingObjectExistence(instructor);
        return entityManager.merge(instructor);
    }

    @Override
    public void deleteById(int id) {
        Instructor foundOne = entityManager.createQuery("From Instructor i WHERE i.id=:idParam",Instructor.class)
                .setParameter("idParam",id).getSingleResult();
        entityManager.remove(foundOne);
    }

    @Override
    public void deleteByPhoneNumber(Long phone_number) {
        Instructor foundOne = entityManager.createQuery("From Instructor i WHERE i.phoneNumber=:numberParam",Instructor.class)
                .setParameter("numberParam",phone_number).getSingleResult();
        entityManager.remove(foundOne);
    }

    @Override
    public Instructor update(Instructor instructor, int id) {
        Instructor foundOne = entityManager.createQuery("FROM Instructor i WHERE i.id=:idParam ",Instructor.class).setParameter("idParam",id).getSingleResult();
        foundOne.setName(instructor.getName());
        foundOne.setAddress(instructor.getAddress());
        foundOne.setCourseList(instructor.getCourseList());
        foundOne.setPhoneNumber(instructor.getPhoneNumber());
        return entityManager.merge(foundOne);
    }

    @Override
    public Instructor updateByPhoneNumber(Instructor instructor, Long phone_number) {
        Instructor foundOne = entityManager.createQuery("FROM Instructor i WHERE i.phoneNumber=:phoneParam ",Instructor.class).setParameter("phoneParam",phone_number).getSingleResult();
        foundOne.setName(instructor.getName());
        foundOne.setAddress(instructor.getAddress());
        foundOne.setCourseList(instructor.getCourseList());
        return entityManager.merge(foundOne);
    }

    private void checkingObjectExistence(Instructor instructor){
        List<Instructor> foundInstructors = entityManager.createQuery("FROM Instructor i WHERE i.phoneNumber=:phoneParam ",Instructor.class)
                .setParameter("phoneParam",instructor.getPhoneNumber()).getResultList();
        if(foundInstructors.size() > 0)
            throw new AlreadyExistsException(""+instructor+"\n"+"this instructor already exists !");
    }
}
