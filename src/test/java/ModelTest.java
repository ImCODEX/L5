import Model.*;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Test Class for Model
 */

public class ModelTest {
    private Course courseBazeDeDate;
    private Course courseStructuriDeDate;
    private Student studentRazvan;
    private Student studentMarius;
    private Student studentAndrei;
    private Student studentCodrut;
    private Teacher teacherDorel;
    private Teacher teacherDor;
    private List<Student> students = new ArrayList<>();

    /**
     * Setup method
     * instantiates various Teacher, Student, Course Objects
     * also instantiates various ArrayLists for complete
     * declaration of Teacher, Student, Course Objects
     */
    @BeforeEach
    public void setup(){
        teacherDorel = new Teacher("Dorel", "Bob", 1, new ArrayList<>());
        teacherDor = new Teacher("Dor", "Dob", 2, new ArrayList<>());
        studentRazvan = new Student("Razvan", "Postescu", 103050, 0, new ArrayList<>());
        studentMarius = new Student("Marius", "Pop", 103051, 0, new ArrayList<>());
        studentAndrei = new Student("Andrei", "Marian", 103052, 0, new ArrayList<>());
        studentCodrut = new Student("Codrut", "Irimie", 103053, 0, new ArrayList<>());
        courseBazeDeDate = new Course(0, "Baze de date",  5,  6);
        courseStructuriDeDate = new Course(1, "Structuri de date si algoritmi", 2,  5);

        List<Course> teacherDorelCourses = new ArrayList<>();
        teacherDorelCourses.add(courseBazeDeDate);
        teacherDorel.setCourses(teacherDorelCourses);

        List<Course> teacherDorCourses = new ArrayList<>();
        teacherDorCourses.add(courseStructuriDeDate);
        teacherDor.setCourses(teacherDorCourses);

        List<Student> courseBazeDeDateStudents = new ArrayList<>();
        courseBazeDeDateStudents.add(studentRazvan);
        courseBazeDeDateStudents.add(studentMarius);

        List<Course> studentRazvanCourses = new ArrayList<>();
        studentRazvanCourses.add(courseBazeDeDate);
        studentRazvan.setEnrolledCourses(studentRazvanCourses);

        List<Course> studentMariusCourses = new ArrayList<>();
        studentMariusCourses.add(courseBazeDeDate);
        studentMarius.setEnrolledCourses(studentMariusCourses);

        List<Student> courseStructuriDeDateStudents = new ArrayList<>();
        courseStructuriDeDateStudents.add(studentAndrei);
        courseStructuriDeDateStudents.add(studentCodrut);

        List<Course> studentAndreiCourses = new ArrayList<>();
        studentAndreiCourses.add(courseStructuriDeDate);
        studentAndrei.setEnrolledCourses(studentAndreiCourses);

        List<Course> studentCodrutCourses = new ArrayList<>();
        studentCodrutCourses.add(courseStructuriDeDate);
        studentCodrut.setEnrolledCourses(studentCodrutCourses);

        List<Teacher> courseBazeDeDateTeachers = new ArrayList<>();
        List<Teacher> courseStructuriDeDateTeachers = new ArrayList<>();
        courseBazeDeDateTeachers.add(teacherDorel);
        courseBazeDeDateTeachers.add(teacherDor);


        students.add(studentAndrei);
        students.add(studentCodrut);
        students.add(studentRazvan);
        students.add(studentMarius);

    }

    /**
     * Test for toString() methods
     * of Teacher, Student, Course Objects
     */
    @Test
    public void printAll(){
        System.out.println(studentRazvan.toString());
        System.out.println(studentMarius.toString());
        System.out.println(studentAndrei.toString());
        System.out.println(studentCodrut.toString());
    }

    /**
     * Test for good insertion of ArrayList<Course> in Student Class
     * and good functionality of Class Student getter
     */
    @Test
    public void testEnrolledCourses(){
        List<Course> studentRazvanCourses = new ArrayList<>();
        studentRazvanCourses.add(courseBazeDeDate);
        assertEquals(studentRazvan.getEnrolledCourses(), studentRazvanCourses);

        List<Course> studentMariusCourses = new ArrayList<>();
        studentMariusCourses.add(courseBazeDeDate);
        assertEquals(studentMarius.getEnrolledCourses(), studentMariusCourses);

    }


    /**
     * Test for good insertion of ArrayList<Course> in Teacher Class
     * and good functionality of Class Teacher getter
     */
    @Test
    public void testListOfCourses(){
        List<Course> teacherDorelCourses = new ArrayList<>();
        teacherDorelCourses.add(courseBazeDeDate);
        assertEquals(teacherDorel.getCourses(), teacherDorelCourses);
    }

    /**
     * Test for good insertion of Teacher in Course Class
     */
    @Test
    public void testCoursesTeacher(){
        assertEquals(courseBazeDeDate.getTeacher(), teacherDorel);
        assertNotEquals(courseBazeDeDate.getTeacher(), teacherDor);

        assertEquals(courseStructuriDeDate.getTeacher(), teacherDor);
        assertNotEquals(courseStructuriDeDate.getTeacher(), teacherDorel);
    }

    @Test
    public void testToJSON() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        String serialized_student = new String();

        for(Student s : students) {

            objectMapper.registerModules(new SimpleModule().addSerializer(Student.class, new StudentSerializer()));

            System.out.println(objectMapper.writeValueAsString(s));

            serialized_student += objectMapper.writeValueAsString(s);

            serialized_student += "\r\n";



            writer.writeValue(new File("studentData.json"), serialized_student);
        }
    }

    @Test
    public void readFromJSON() throws IOException{
        Reader reader = new BufferedReader(new FileReader("data.json"));

        List<Student> students = new LinkedList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for (JsonNode n: parser){
            Student s = new Student();

            s.setStudentId(n.path("studentId").asInt());
            s.setFirstName(n.path("firstName").asText());
            s.setLastName(n.path("lastName").asText());
            s.setTotalCredits(n.path("totalCredits").asInt());


            String courses = n.path("enrolledCourses").asText();
            String[] splits =  courses.replace("[","").replace("]","").split(",");
            List<Integer> coursesID = new ArrayList<>(Arrays.asList(splits)).stream().map(Integer::valueOf).toList();
            List<Course> courseList = new ArrayList<>(coursesID.stream().map(x -> x))


            JsonNode add = n.path("address");
            a.setCity(add.path("city").asText());
            a.setStreet(add.path("street").asText());

            s.setAddress(a);
            students.add(s);
        }
        reader.close();
    }

}
