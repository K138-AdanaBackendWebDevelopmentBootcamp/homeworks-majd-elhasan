package dev.patika.SchoolManagementSystem03.DAO.Interfaces;


public interface ICourseDAO<Course> extends IBaseDAO<Course> {
    void deleteByCode(String Code);
    Course updateByCode(Course course, String code);
}
