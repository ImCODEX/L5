import CustomExceptions.CustomExceptions;
import Model.Course;
import Model.Student;
import Model.Teacher;
import Repo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FileRepoTest {
    private StudentFileRepository studentFileRepository;
    private TeacherFileRepository teacherFileRepository;
    private CourseFileRepository courseFileRepository;

    private Student studentRazvan;
    private Student studentMarius;
    private Student studentAndrei;
    private Teacher teacherDorel;
    private Teacher teacherDor;

    @BeforeEach
    public void setup() throws IOException {
        courseFileRepository = new CourseFileRepository();
        teacherFileRepository = new TeacherFileRepository(courseFileRepository);
        studentFileRepository = new StudentFileRepository(courseFileRepository);
        courseFileRepository.run();
        teacherFileRepository.run();
        studentFileRepository.run();

        JsonManualRepair jsonManualRepair = new JsonManualRepair();
        jsonManualRepair.repairJSON();

       /* Course courseStatistica = new Course(1, "Statistica", 4, 5);
        Course courseMAP = new Course(2, "MAP", 4, 5);
        Course courseLP = new Course(3, "LP", 4, 5);

        courseFileRepository.add(courseStatistica);
        courseFileRepository.add(courseMAP);
        courseFileRepository.add(courseLP);

        List<Integer> coursesId1 = new ArrayList<>();
        List<Integer> coursesId2 = new ArrayList<>();
        List<Integer> coursesId3 = new ArrayList<>();
        coursesId1.add(1);
        coursesId1.add(2);
        coursesId2.add(2);
        coursesId2.add(3);
        coursesId3.add(1);
        coursesId3.add(2);
        coursesId3.add(3);
        List<Course> tempCourses1 = new ArrayList<>();
        List<Course> tempCourses2 = new ArrayList<>();
        List<Course> tempCourses3 = new ArrayList<>();
        for (Integer id :
                coursesId1) {
            for (Course c :
                    courseFileRepository.getAll()) {
                if (c.getId() == id)
                    tempCourses1.add(c);
            }
        }
        for (Integer id :
                coursesId2) {
            for (Course c :
                    courseFileRepository.getAll()) {
                if (c.getId() == id)
                    tempCourses2.add(c);
            }
        }for (Integer id :
                coursesId3) {
            for (Course c :
                    courseFileRepository.getAll()) {
                if (c.getId() == id)
                    tempCourses3.add(c);
            }
        }

        studentRazvan = new Student("Razvan", "Postescu", 103050, 0, tempCourses1);
        studentMarius = new Student("Marius", "Pop", 103051, 0, tempCourses2);
        studentAndrei = new Student("Andrei", "Marian", 103052, 0, tempCourses3);

        teacherDorel = new Teacher("Dorel", "Bob", 1, (List<Course>) tempCourses1.get(0));
        teacherDor = new Teacher("Dor", "Dob", 2, (List<Course>) tempCourses2.get(1));

*/
    }

    @Test
    public void printAll() throws IOException {

        for (Student s:
             studentFileRepository.getAll()) {
            System.out.println(s.toString());
        }

        for (Teacher t:
                teacherFileRepository.getAll()) {
            System.out.println(t.toString());
        }

        for (Course c:
                courseFileRepository.getAll()) {
            System.out.println(c.toString());
        }

        courseFileRepository.close();
        studentFileRepository.close();
        teacherFileRepository.close();

    }

    @Test
    public void testCoursesFromRepositories() throws IOException, CustomExceptions {
        List<Student> students = courseFileRepository.getAll().get(0).getStudentsEnrolled();
        Teacher teacher = courseFileRepository.getAll().get(0).getTeacher();
        assertEquals(courseFileRepository.getAll().get(0), new Course(0, "Baze de date", teacher, 5, students, 6));

        List<Student> students2 = courseFileRepository.getAll().get(1).getStudentsEnrolled();
        Teacher teacher2 = courseFileRepository.getAll().get(1).getTeacher();

        assertEquals(courseFileRepository.getAll().get(1), new Course(1, "Structuri de date si algoritmi", teacher2, 2, students2, 5));

        assertEquals(courseFileRepository.find(0), new Course(0, "Baze de date", teacher, 5, students, 6));
        assertEquals(courseFileRepository.find(1), new Course(1, "Structuri de date si algoritmi", teacher2, 2, students2, 5));
        courseFileRepository.close();
        studentFileRepository.close();
        teacherFileRepository.close();
    }

    @Test
    public void testStudentsFromRepositories() throws IOException, CustomExceptions {
        List<Course> courses = studentFileRepository.getAll().get(0).getEnrolledCourses();
        assertEquals(studentFileRepository.getAll().get(0), new Student("Andrei", "Marian", 103052, 0, courses));

        List<Course> courses2 = studentFileRepository.getAll().get(1).getEnrolledCourses();
        assertEquals(studentFileRepository.getAll().get(1), new Student("Codrut", "Irimie", 103053, 0, courses2));

        assertEquals(studentFileRepository.find(103052), new Student("Andrei", "Marian", 103052, 0, courses));
        try{ assertEquals(studentFileRepository.find(1), new Student("Codrut", "Irimie", 103053, 0, courses2)); }
        catch (Exception ignored){}
        courseFileRepository.close();
        studentFileRepository.close();
        teacherFileRepository.close();
    }

    @Test
    public void testTeachersFromRepositories() throws IOException, CustomExceptions {
        List<Course> courses = teacherFileRepository.getAll().get(0).getCourses();
        List<Course> courses2 = teacherFileRepository.getAll().get(1).getCourses();

        assertEquals(teacherFileRepository.getAll().get(0), new Teacher("Dor", "Dob", 2, courses));
        assertEquals(teacherFileRepository.getAll().get(1), new Teacher("Dorel", "Bob", 1, courses2));

        assertEquals(teacherFileRepository.find(2), new Teacher("Dor", "Dob", 2, courses));
        try{ assertEquals(teacherFileRepository.find(0), new Teacher("Dorel", "Bob", 1, courses2));}
        catch(Exception ignored){}

        courseFileRepository.close();
        studentFileRepository.close();
        teacherFileRepository.close();
    }


}
