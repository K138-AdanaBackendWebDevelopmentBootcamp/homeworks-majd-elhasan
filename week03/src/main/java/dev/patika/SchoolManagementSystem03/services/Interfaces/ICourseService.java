package dev.patika.SchoolManagementSystem03.services.Interfaces;

public interface ICourseService<Course> extends IBaseService<Course>{
    void deleteByCode(String code);

    Course updateByCode(Course course, String code);
}
