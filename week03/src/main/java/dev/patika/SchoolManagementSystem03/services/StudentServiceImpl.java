// -1- the Request journey start from here -controller layer-
//--------------------------------------------------------------
// (2) and then It's gonna to go to the -service layer-     ↓
//--------------------------------------------------------------
// -3- and then It's gonna to go to DAO layer
// -4- over there It will be handled by entity manager which is controlled by Spring boot.
package dev.patika.SchoolManagementSystem03.services;

import dev.patika.SchoolManagementSystem03.DAO.Interfaces.IStudentDAO;
import dev.patika.SchoolManagementSystem03.models.Student;
import dev.patika.SchoolManagementSystem03.services.Interfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
// We mark beans with @Service to indicate that they're holding the business logic. Besides, being used in the service layer, there Isn't any other special use for this annotation.
@Service
public class StudentServiceImpl implements IStudentService<Student> {

    private final IStudentDAO<Student> iStudentDAO;
    // we make an instance of IStudentDAO<Student> to have it injected to this object by the constructor (constructor injection) ---HERE WE ARE TALKING ABOUT THE "DI" DEPENDENCY INJECTION---
    // DI types: Dependency Injection types
    // field injection
    // setter injection
    // constructor injection
    @Autowired
    public StudentServiceImpl(IStudentDAO<Student> iStudentDAO){
        this.iStudentDAO = iStudentDAO;
    }
    @Override
    public List<Student> findAll() {
        return iStudentDAO.findAll();
    }

    @Override
    public Student findById(int id) {
        return iStudentDAO.findById(id);
    }

    @Override
    public Student save(Student student){
        return iStudentDAO.save(student);
    }

    @Override
    public void deleteById(int id) {
        iStudentDAO.deleteById(id);
    }

    @Override
    public Student update(Student student,int id) {
        return iStudentDAO.update(student, id);
    }
}
