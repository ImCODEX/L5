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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        courseFileRepository.run();
        teacherFileRepository.run();
        studentFileRepository.run();

        JsonManualRepair jsonManualRepair = new JsonManualRepair();
        jsonManualRepair.repairJSON();
    }

    @Test
    public void testAddCourse() throws CustomExceptions, IOException {
        controller.addCourse(5, "Algoritmica Grafelor", 5, 6);
        assertEquals(controller.findCourseById(5),
                new Course(5, "Algoritmica Grafelor", 5, 6));
        courseFileRepository.close();
        studentFileRepository.close();
        teacherFileRepository.close();
    }

    @Test
    public void testAddStudent() throws CustomExceptions, IOException {
        List<Integer> listOfId = new ArrayList<>();
        listOfId.add(1);
        controller.addStudent("nume", "prenume", 555, 10, listOfId);
        assertEquals(controller.findStudentById(555), new Student("nume", "prenume", 555, 10, new ArrayList<Course>(controller.findStudentById(555).getEnrolledCourses())));

        courseFileRepository.close();
        studentFileRepository.close();
        teacherFileRepository.close();
    }

    @Test
    public void testAddTeacher() throws CustomExceptions, IOException {
        List<Integer> coursesID = new ArrayList<>();
        coursesID.add(1);
        List<Course> courses = new ArrayList<>();
        courses.add(controller.findCourseById(1));
        controller.addTeacher("Dorel", "Bob", 1, coursesID);
        assertEquals(controller.findTeacherById(1), new Teacher("Dorel", "Bob", 1, courses));

        courseFileRepository.close();
        studentFileRepository.close();
        teacherFileRepository.close();
    }

    @Test
    public void testUpdateCourse(){

    }

}
