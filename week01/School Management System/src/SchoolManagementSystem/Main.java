package SchoolManagementSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Course Course01 = new Course("Mathematics", "MATH", 6);
        Course Course02 = new Course("Geography", "GEO", 2);
        Course Course03 = new Course("Physic", "PHYS", 4);
        Course Course04 = new Course("Chemistry", "CHEM", 4);
        Course Course05 = new Course("Philosophy", "PHiL", 4);
        Course Course06 = new Course("Religion", "RLGN", 4);
        List<Course> courseList01 = new ArrayList<Course>(Arrays.asList(Course01, Course02));
        List<Course> courseList02 = new ArrayList<Course>(Arrays.asList(Course03, Course04));
        List<Course> courseList03 = new ArrayList<Course>(Arrays.asList(Course05, Course06));

        Instructor i1 = new PermanentInstructor("Mecid", "mecidiye", 5355517164L,courseList01,5000);
        Instructor i2 = new PermanentInstructor("Mahmut", "mahmudiye", 5348799854L,courseList01,4800);
        Instructor i3 = new VisitingResearcher("furkan", "bursa", 5348765687L,courseList01,150);

        Student s1=new Student("Nur",LocalDate.of(1995,02,27),"inegöl","erkek");

        System.out.println(s1.toString());
        System.out.println(Course01.toString());
    }
}
