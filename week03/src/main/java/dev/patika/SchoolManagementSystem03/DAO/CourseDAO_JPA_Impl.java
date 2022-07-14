package dev.patika.SchoolManagementSystem03.DAO;

import dev.patika.SchoolManagementSystem03.DAO.Interfaces.ICourseDAO;
import dev.patika.SchoolManagementSystem03.Exceptions.AlreadyExistsException;
import dev.patika.SchoolManagementSystem03.models.Course;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
// I'm going to work on this class later...
@Repository
public class CourseDAO_JPA_Impl implements ICourseDAO<Course> {
    private final EntityManager entityManager;

    public CourseDAO_JPA_Impl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)  // we use readOnly attribute to lower the load on the program and prevent saving processes from occurring.
    public List<Course> findAll() {
        return entityManager.createQuery("FROM Course",Course.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Course findById(int id) {
        return (Course) entityManager.createQuery("FROM Course c WHERE c.id =:idParam")
                .setParameter("idParam",id).getSingleResult();
    }

    @Override
    @Transactional  // without @Transactional annotation we can not post an object  ,and we get internal server error 500
    public Course save(Course course){
        checkingObjectExistence(course);
        return entityManager.merge(course);
    }
    @Override
    @Transactional
    public void deleteById(int id) {
        Course foundOne = entityManager.createQuery("FROM Course c WHERE c.id=:idParam",Course.class).setParameter("idParam",id).getSingleResult();
        entityManager.remove(foundOne);
    }
    @Override
    @Transactional
    public void deleteByCode(String CourseCode){
        Course foundOne = entityManager.createQuery("FROM Course c WHERE c.courseCode=:CodeParam",Course.class).setParameter("CodeParam",CourseCode).getSingleResult();
        entityManager.remove(foundOne);
    }

    @Override
    @Transactional
    public Course update(Course course,int id) {
        Course foundOne = entityManager.createQuery("FROM Course c WHERE c.id=:idParam ",Course.class).setParameter("idParam",id).getSingleResult();
        foundOne.setCourseName(course.getCourseName());
        foundOne.setCourseCode(course.getCourseCode());
        foundOne.setInstructor(course.getInstructor());
        foundOne.setStudentList(course.getStudentList());
        return entityManager.merge(foundOne);
    }
    @Override
    @Transactional
    public Course updateByCode(Course course, String code) {
        Course foundOne = entityManager.createQuery("FROM Course c WHERE c.courseCode=:CodeParam ",Course.class).setParameter("CodeParam",code).getSingleResult();
        foundOne.setCourseName(course.getCourseName());
        foundOne.setCourseCode(course.getCourseCode());
        foundOne.setInstructor(course.getInstructor());
        foundOne.setStudentList(course.getStudentList());
        return entityManager.merge(foundOne);
    }

    private void checkingObjectExistence(Course course){
        List<Course> foundCourses = entityManager.createQuery("FROM Course c WHERE c.courseCode=:CodeParam",Course.class)
                .setParameter("CodeParam",course.getCourseCode()).getResultList();
        if(foundCourses.size() > 0)
            throw new AlreadyExistsException(""+course+"\n"+"this course already exists !");
    }
}
