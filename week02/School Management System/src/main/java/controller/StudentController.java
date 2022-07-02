package controller;

import models.Student;
import service.StudentService;

import java.util.List;

public class StudentController {
    private StudentService studentService = new StudentService();
    public List<Student> findAllStudents(){
        return studentService.findAll();
    }
    public Student findStudentById(int id){
        return studentService.findById(id);
    }
    public void saveStudent(Student student){
        studentService.saveToDatabase(student);
    }
    public void deleteStudent(Student student){
        studentService.deleteFromDatabase(student);
    }
    public void deleteStudentById(int id){
        studentService.deleteFromDatabaseById(id);
    }
    public void updateStudent(Student student){
        studentService.updateOnDatabase(student);
    }
}
