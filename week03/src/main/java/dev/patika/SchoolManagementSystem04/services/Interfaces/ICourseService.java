package dev.patika.SchoolManagementSystem04.services.Interfaces;
// this interface just conjunction between IBaseService and CourseService_implementation classes
// this interface contains the common used methods in the inheritor --Course-- service (sub) classes as well.
public interface ICourseService<Course> extends IBaseService<Course>{
    void deleteByCode(String code);

    Course updateByCode(Course course, String code);
}
