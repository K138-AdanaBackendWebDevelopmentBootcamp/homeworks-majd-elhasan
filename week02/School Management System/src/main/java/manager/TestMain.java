package manager;

import controller.StudentController;
import models.*;
import utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        StudentController studentController = new StudentController();

        System.out.println("Started...");
        if(checkData() == 0){

            // save

            Student newStudent = new Student("sami yusuf",LocalDate.of(1980,7,21),"tahran","male");
            studentController.saveStudent(newStudent);

            saveData();
        }

        List<Student> studentList = studentController.findAllStudents();

        // update
        Student updatedStudent = studentList.get(0);
        updatedStudent.setName("fate");
        studentController.updateStudent(updatedStudent);

        studentController.deleteStudentById(1);
        for (Student student:studentList
             ) {
            System.out.println(student);
        }
        //System.out.println(studentController.findStudentById(1));

    }

    private static int checkData() {
        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");
        return em.createQuery("FROM Student",Student.class).getResultList().size();
    }

    private static void saveData() {

        Instructor i1 = new PermanentInstructor("Mecid", "mecidiye", 5355517164L,5000);
        Instructor i2 = new PermanentInstructor("Mahmut", "mahmudiye", 5348799854L,4800);
        Instructor i3 = new VisitingResearcher("furkan","Bursa",5348745871L,150);

        Course course01 = new Course("Mathematics", "MATH", 6,i1);
        Course course02 = new Course("Geography", "GEO", 2,i1);
        Course course03 = new Course("Physic", "PHYS", 4,i2);
        Course course04 = new Course("Chemistry", "CHEM", 4,i2);
        Course course05 = new Course("Philosophy", "PHiL", 4,i3);
        Course course06 = new Course("Religion", "RLGN", 4,i3);

        i1.setCourse(course01,course02);
        i2.setCourse(course03,course04);
        i3.setCourse(course05,course06);

        Student s1=new Student("Nur", LocalDate.of(2001,5,18),"Bursa","female");
        s1.setCourse(course01,course02,course04,course06);
        Student s2=new Student("Jade", LocalDate.of(2008,11,23),"Londra","female");
        s2.setCourse(course04,course03);
        Student s3=new Student("Sümeyye", LocalDate.of(2005,2,9),"Halep","female");
        s3.setCourse(course05,course02,course06);
        Student s4=new Student("ChenLi", LocalDate.of(1998,7,17),"Pekin","male");

        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");

        try {
            em.getTransaction().begin();
            // ...
            em.persist(s1);

            System.out.println("All data are persisted");

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }
}
