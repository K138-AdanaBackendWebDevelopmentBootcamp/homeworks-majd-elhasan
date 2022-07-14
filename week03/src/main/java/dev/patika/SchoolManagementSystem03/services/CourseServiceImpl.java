package dev.patika.SchoolManagementSystem03.services;

import dev.patika.SchoolManagementSystem03.DAO.Interfaces.ICourseDAO;
import dev.patika.SchoolManagementSystem03.models.Course;
import dev.patika.SchoolManagementSystem03.services.Interfaces.ICourseService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService<Course> {

    private final ICourseDAO<Course> iCourseDAO;

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
