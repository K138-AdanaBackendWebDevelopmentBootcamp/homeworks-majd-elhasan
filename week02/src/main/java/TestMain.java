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
        if(checkData() >= 0){

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

        Student s1=new Student("Nur", LocalDate.of(2001,5,18),"Bursa","female");
        Student s2=new Student("Jade", LocalDate.of(2008,11,23),"Londra","female");
        Student s3=new Student("Sümeyye", LocalDate.of(2005,2,9),"Halep","female");
        Student s4=new Student("ChenLi", LocalDate.of(1998,7,17),"Pekin","male");
        Student s5 = new Student("Mustafa", LocalDate.of(1999,5,14),"Erzurum-ispir","male");
        Student s6 = new Student("Hatice", LocalDate.of(1998,2,3),"Muş-varto","female");
        Student s7 = new Student("Sefa", LocalDate.of(1995,8,9),"Diyarbakır-dicle","male");
        Student s8 = new Student("Merve", LocalDate.of(1994,11,15),"Artvin-şavşat","female");
        Student s9 = new Student("Mecid", LocalDate.of(1995,2,27),"Bursa-inegöl","male");
        Student s10 = new Student("Mahmut", LocalDate.of(2001,7,30),"Adana-seyhan","male");
        Student s11 = new Student("Emine", LocalDate.of(1997,1,18),"Samsun-merkez","female");
        Student s12 = new Student("Ibrahim", LocalDate.of(1989,6,22),"Bursa-osmangazi","male");

        Course c1 = new Course("mathematics","MATH01",6);
        Course c2 = new Course("english","ENG01",6);
        Course c3 = new Course("physics","PHYS01",6);
        Course c4 = new Course("chemistry","CHEM01",4);
        Course c5 = new Course("islam_religion","ISLAM01",6);
        Course c6 = new Course("music","MUS01",2);
        Course c7 = new Course("programming","PROG01",5);
        Course c8 = new Course("Geography", "GEO", 2);
        Course c9 = new Course("Philosophy", "PHiL", 4);

        Instructor i1 = new PermanentInstructor("Mecid", "mecidiye", 5355517164L,5000);
        Instructor i2 = new PermanentInstructor("John", "Urfa", 5348799854L,4800);
        Instructor i3 = new VisitingResearcher("furkan","Bursa",5348745871L,150);
        Instructor i4 = new PermanentInstructor("Zeynep","Bitlis",53587496581L,6200);
        Instructor i5 = new VisitingResearcher("Mansur","Aydın",53777889642L,5800);
        Instructor i6 = new PermanentInstructor("Koray","Adana",53598745214L,7000);
        Instructor i7 = new PermanentInstructor("Aytaç","İstanbul",52645847144L,5500);
        Instructor i8 = new PermanentInstructor("Sude","Ankara",56987425745L,6500);


        i1.setCourse(c3,c4);
        i2.setCourse(c7);
        i3.setCourse(c6);
        i4.setCourse(c2);
        i5.setCourse(c5);
        i6.setCourse(c8,c9);
        i7.setCourse(c2);
        i8.setCourse(c1);

        c1.setStudent(s9,s11);
        c2.setStudent(s12,s5,s6,s4);
        c3.setStudent(s7,s10,s9,s12);
        c4.setStudent(s9,s12,s5,s8,s10,s4);
        c5.setStudent(s9,s10,s2,s3);
        c6.setStudent(s9,s10,s11,s5);
        c7.setStudent(s5,s7,s8,s10,s9);

        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");

        try {
            em.getTransaction().begin();
            // ...
            em.persist(s1);em.persist(s2);em.persist(s3);em.persist(s4);
            em.persist(s5);em.persist(s6);em.persist(s7);em.persist(s8);
            em.persist(s9);em.persist(s10);em.persist(s11);em.persist(s12);

            em.persist(c1);em.persist(c2);em.persist(c3);em.persist(c4);
            em.persist(c5);em.persist(c6);em.persist(c7);em.persist(c8);em.persist(c9);

            em.persist(i1);em.persist(i2);em.persist(i3);em.persist(i4);
            em.persist(i5);em.persist(i6);em.persist(i7);em.persist(i8);

            System.out.println("All data are persisted");

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }
}
