// -1- the Request journey start from here -controller layer-
// -2- and then It's gonna to go to the -service layer-
//--------------------------------------------------------------
// (3) and then It's gonna to go to DAO layer               ↓
//--------------------------------------------------------------
// -4- over there It will be handled by entity manager which is controlled by Spring boot.
package dev.patika.SchoolManagementSystem03.DAO;

import dev.patika.SchoolManagementSystem03.DAO.Interfaces.IStudentDAO;
import dev.patika.SchoolManagementSystem03.Exceptions.AlreadyExistsException;
import dev.patika.SchoolManagementSystem03.models.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;
//@Repository’s job is to catch persistence-specific exceptions and re-throw them as one of Spring’s unified unchecked exceptions.

@Repository
public class StudentDAO_JPA_Impl implements IStudentDAO<Student> {

    private final EntityManager entityManager;
    // we make an instance of EntityManager to have it injected to this object by the constructor (constructor injection) ---HERE WE ARE TALKING ABOUT THE "DI" DEPENDENCY INJECTION---
    // DI types: Dependency Injection types
    // field injection
    // setter injection
    // constructor injection
    public StudentDAO_JPA_Impl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    @Transactional(readOnly = true)  // we use readOnly attribute to lower the load on the program and prevent saving processes from occurring.
    public List<Student> findAll() {
        return entityManager.createQuery("FROM Student",Student.class).getResultList();
    }
    @Override
    @Transactional(readOnly = true)
    public Student findById(int id) {
        return (Student) entityManager.createQuery("FROM Student s WHERE s.id =:idParam")
                .setParameter("idParam",id).getSingleResult();
    }

    @Override
    @Transactional  // without @Transactional annotation we can not post an object  ,and we get internal server error 500
    public Student save(Student student){
        checkingObjectExistence(student);
        return entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Student foundStudent =(Student) entityManager.createQuery("FROM Student s WHERE s.id =:idParam")
                .setParameter("idParam",id).getSingleResult();
        entityManager.remove(foundStudent);
    }

    @Override
    @Transactional
    public Student update(Student student,int id) {
        Student foundStudent =(Student) entityManager.createQuery("FROM Student s WHERE s.id =:idParam")
                .setParameter("idParam",id).getSingleResult();
        foundStudent.setName(student.getName());
        foundStudent.setAddress(student.getAddress());
        foundStudent.setBirth_date(student.getBirth_date());
        foundStudent.setGender(student.getGender());
        foundStudent.setCourseList(student.getCourseList());
        /*
         * Here we set the instance variables of the given student instance to the new instantiated student object which has the given ID.
         * to do some comparison
         */
        return entityManager.merge(foundStudent);

    }
    private void checkingObjectExistence(Student student){
        // in this Method we check if the posted/updated  student name ,birthdate and address are same with a single data of our student database ,if they are the same we'll throw an Exception and won't save the coming data
        // instead of that off course we could check that by student ID but in this project we don't have an ID .
        List<Student> foundStudents = entityManager.createQuery("FROM Student s WHERE s.name =:nameParam AND s.birth_date=:birthDateParam AND s.address=:addressParam",Student.class)
                .setParameter("nameParam",student.getName())
                .setParameter("birthDateParam",student.getBirth_date())
                .setParameter("addressParam",student.getAddress())
                .getResultList();
        if(foundStudents.size() > 0)
            throw new AlreadyExistsException(""+student+"\n"+"this student already exists !");
    }
}
