import Controller.Controller;
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

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    private StudentFileRepository studentFileRepository;
    private TeacherFileRepository teacherFileRepository;
    private CourseFileRepository courseFileRepository;
    private Controller controller;

    @BeforeEach
    public void setup() throws IOException {
        courseFileRepository = new CourseFileRepository();
        teacherFileRepository = new TeacherFileRepository(courseFileRepository);
        studentFileRepository = new StudentFileRepository(courseFileRepository);
        controller = new Controller(courseFileRepository, studentFileRepository, teacherFileRepository);
        controller.startInput();

        JsonManualRepair jsonManualRepair = new JsonManualRepair();
        jsonManualRepair.repairJSON();
    }

    @Test
    public void testAddCourse() throws CustomExceptions, IOException {
        controller.addCourse(5, "Algoritmica Grafelor", 5, 6);
        assertEquals(controller.findCourseById(5),
                new Course(5, "Algoritmica Grafelor", 5, 6));
        controller.saveInput();
    }

    @Test
    public void testAddStudent() throws CustomExceptions, IOException {
        List<Integer> listOfId = new ArrayList<>();
        listOfId.add(1);
        controller.addStudent("nume", "prenume", 555, 10, listOfId);
        assertEquals(controller.findStudentById(555), new Student("nume", "prenume", 555, 10, new ArrayList<>(controller.findStudentById(555).getEnrolledCourses())));

        controller.saveInput();
    }

    @Test
    public void testAddTeacher() throws CustomExceptions, IOException {
        List<Integer> coursesID = new ArrayList<>();
        coursesID.add(1);
        List<Course> courses = new ArrayList<>();
        courses.add(controller.findCourseById(1));
        controller.addTeacher("Dorel", "Bob", 1, coursesID);
        assertEquals(controller.findTeacherById(1), new Teacher("Dorel", "Bob", 1, courses));

        controller.saveInput();
    }

    @Test
    public void testUpdateCourse() throws CustomExceptions, IOException {
        int id = 0;
        controller.updateCourse(0, 0, "cursTest", 999, 9);
        assertEquals(controller.findCourseById(0), new Course(0, "cursTest", 999, 9));

        controller.saveInput();
    }

    @Test
    public void testUpdateStudent() throws CustomExceptions, IOException {
        int id = 0;
        List<Integer> listIds = new ArrayList<>();
        listIds.add(0);
        controller.updateStudent(0, "studenttest", "whatever", 5, 2, listIds);
        assertEquals(controller.findStudentById(5), new Student("studenttest", "whatever", 5, 2, controller.findStudentById(5).getEnrolledCourses()));

        controller.saveInput();
    }

    @Test
    public void testUpdateTeacher() throws CustomExceptions, IOException {
        int id = 0;
        List<Integer> listIds = new ArrayList<>();
        listIds.add(0);
        controller.updateTeacher(1, "testTeacher", "whatever", 4, listIds);
        assertEquals(controller.findTeacherById(4), new Teacher("testTeacher", "whatever", 4, controller.findTeacherById(4).getCourses()));

        controller.saveInput();
    }

    @Test
    public void testDeleteCourse() throws CustomExceptions, IOException {
        assertEquals(controller.getCourses().size(), 3);
        controller.deleteCourse(controller.findCourseById(0));
        assertEquals(controller.getCourses().size(), 2);

        controller.saveInput();
    }

    @Test
    public void testDeleteStudent() throws CustomExceptions, IOException {
        assertEquals(controller.getStudents().size(), 4);
        controller.deleteStudent(controller.findStudentById(103050));
        assertEquals(controller.getStudents().size(), 3);

        controller.saveInput();
    }

    @Test
    public void testDeleteTeacher() throws CustomExceptions, IOException {
        assertEquals(controller.getTeacher().size(), 2);
        controller.deleteTeacher(controller.findTeacherById(2));
        assertEquals(controller.getTeacher().size(), 1);

        controller.saveInput();
    }

    @Test
    public void testSortStudentsByEnrolledCourses() throws IOException, CustomExceptions {
        assertNotEquals(controller.sortStudentsByEnrolledCourses(), controller.getStudents());
        assertEquals(controller.sortStudentsByEnrolledCourses().get(0), controller.findStudentById(103051));
        controller.saveInput();
    }

    @Test
    public void testSortCourseByCredits() throws IOException {
        assertEquals(controller.sortCourseByCredits(), controller.getCourses());
        controller.saveInput();
    }

    @Test
    public void testFilterStudentsByLessThenXCourses() throws IOException {
        assertNotEquals(controller.filterStudentsByLessThenXCourses(2), controller.getStudents());
        for (Student s:
                controller.filterStudentsByLessThenXCourses(2)) {
            assertTrue(s.getEnrolledCourses().size() <= 2);
        }
        controller.saveInput();
    }

    @Test
    public void testFilterCourseByMaxEnrollment() throws IOException {
        assertNotEquals(controller.filterCourseByMaxEnrollment(5), controller.getCourses());
        for (Course c:
             controller.filterCourseByMaxEnrollment(5)) {
            assertTrue(c.getMaxEnrollment() <= 5);
        }

        controller.saveInput();
    }


}
