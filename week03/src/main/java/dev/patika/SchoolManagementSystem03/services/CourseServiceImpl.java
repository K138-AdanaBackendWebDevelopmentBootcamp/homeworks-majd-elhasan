// -1- the Request journey start from here -controller layer-
//--------------------------------------------------------------
// (2) and then It's gonna to go to the -service layer-     ↓
//--------------------------------------------------------------
// -3- and then It's gonna to go to DAO layer
// -4- over there It will be handled by entity manager which is controlled by Spring boot.
package dev.patika.SchoolManagementSystem03.services;

import dev.patika.SchoolManagementSystem03.DAO.Interfaces.ICourseDAO;
import dev.patika.SchoolManagementSystem03.models.Course;
import dev.patika.SchoolManagementSystem03.models.PermanentInstructor;
import dev.patika.SchoolManagementSystem03.services.Interfaces.ICourseService;
import dev.patika.SchoolManagementSystem03.services.Interfaces.IInstructorService;
import org.springframework.stereotype.Service;
import java.util.List;
// We mark beans with @Service to indicate that they're holding the business logic. Besides, being used in the service layer, there Isn't any other special use for this annotation.
@Service
public class CourseServiceImpl implements ICourseService<Course> {

    private final ICourseDAO<Course> iCourseDAO;
    // we make an instance of ICourseDAO<Course> to have it injected to this object by the constructor (constructor injection) ---HERE WE ARE TALKING ABOUT THE "DI" DEPENDENCY INJECTION---
    // DI types: Dependency Injection types
    // field injection
    // setter injection
    // constructor injection
    public CourseServiceImpl(ICourseDAO<Course> iCourseDAO) {
        this.iCourseDAO = iCourseDAO;
    }

    @Override
    public List<Course> findAll() {
        return iCourseDAO.findAll();
    }

    @Override
    public Course findById(int id) {
        return iCourseDAO.findById(id);
    }

    @Override
    public Course save(Course course) {
        return iCourseDAO.save(course);
    }

    @Override
    public void deleteById(int id) {
        iCourseDAO.deleteById(id);
    }

    public void deleteByCode(String code){iCourseDAO.deleteByCode(code);}

    @Override
    public Course update(Course course, int id) {
        return iCourseDAO.update(course,id);
    }

    public Course updateByCode(Course course, String code){return iCourseDAO.updateByCode(course,code);}
}
