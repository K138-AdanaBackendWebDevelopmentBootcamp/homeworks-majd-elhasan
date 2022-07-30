// -1- the Request journey start from here -controller layer-
//--------------------------------------------------------------
// (2) and then It's gonna to go to the -service layer-     ↓
//--------------------------------------------------------------
// -3- and then It's gonna to go to DAO layer
// -4- over there It will be handled by entity manager which is controlled by Spring boot.
package dev.patika.SchoolManagementSystem04.services;

import dev.patika.SchoolManagementSystem04.models.Course;
import dev.patika.SchoolManagementSystem04.repository.CourseRepository;
import dev.patika.SchoolManagementSystem04.services.Interfaces.ICourseService;
import org.springframework.stereotype.Service;
import java.util.List;

// We mark beans with @Service to indicate that they're holding the business logic. Besides, being used in the service layer, there Isn't any other special use for this annotation.
@Service
public class CourseServiceImpl implements ICourseService<Course> {

    private final CourseRepository courseRepository;
    // we make an instance of ICourseDAO<Course> to have it injected to this object by the constructor (constructor injection) ---HERE WE ARE TALKING ABOUT THE "DI" DEPENDENCY INJECTION---
    // DI types: Dependency Injection types
    // field injection
    // setter injection
    // constructor injection
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
     return courseRepository.findAll();
   }

    @Override
    public Course findById(int id) {
        return courseRepository.findById(id);
    }

    @Override
    public void deleteByCode(String code) {
        courseRepository.deleteByCourseCode(code);
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Course updateById(Course course,int id){
        course.setId(id);
        return save(course);
    }

    @Override
    public void deleteById(int id) {
        courseRepository.deleteById(id);
    }

}
