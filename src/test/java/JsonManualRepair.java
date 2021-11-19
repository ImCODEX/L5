import Model.*;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * JsonManualRepair used when custom
 * destructor method close() from
 * each FileRepository doesn't get called
 * because of an unhandled Exception
 */

public class JsonManualRepair {
    private Course courseBazeDeDate;
    private Course courseStructuriDeDate;
    private Course courseAlgebra;
    private Student studentRazvan;
    private Student studentMarius;
    private Student studentAndrei;
    private Student studentCodrut;
    private Teacher teacherDorel;
    private Teacher teacherDor;
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();

    /**
     * Setup method
     * instantiates various Teacher, Student, Course Objects
     * also instantiates various ArrayLists for complete
     * declaration of Teacher, Student, Course Objects
     */
    @BeforeEach
    public void setup() {
        teacherDorel = new Teacher("Dorel", "Bob", 1, new ArrayList<>());
        teacherDor = new Teacher("Dor", "Dob", 2, new ArrayList<>());
        studentRazvan = new Student("Razvan", "Postescu", 103050, 0, new ArrayList<>());
        studentMarius = new Student("Marius", "Pop", 103051, 0, new ArrayList<>());
        studentAndrei = new Student("Andrei", "Marian", 103052, 0, new ArrayList<>());
        studentCodrut = new Student("Codrut", "Irimie", 103053, 0, new ArrayList<>());
        courseBazeDeDate = new Course(0, "Baze de date", 5, 6);
        courseStructuriDeDate = new Course(1, "Structuri de date si algoritmi", 2, 5);
        courseAlgebra = new Course(2, "Algebra", 7, 5);

        List<Course> teacherDorelCourses = new ArrayList<>();
        teacherDorelCourses.add(courseBazeDeDate);
        teacherDorel.setCourses(teacherDorelCourses);

        List<Course> teacherDorCourses = new ArrayList<>();
        teacherDorCourses.add(courseStructuriDeDate);
        teacherDorCourses.add(courseAlgebra);
        teacherDor.setCourses(teacherDorCourses);

        List<Student> courseBazeDeDateStudents = new ArrayList<>();
        courseBazeDeDateStudents.add(studentRazvan);
        courseBazeDeDateStudents.add(studentMarius);

        List<Course> studentRazvanCourses = new ArrayList<>();
        studentRazvanCourses.add(courseBazeDeDate);
        studentRazvanCourses.add(courseStructuriDeDate);
        studentRazvan.setEnrolledCourses(studentRazvanCourses);

        List<Course> studentMariusCourses = new ArrayList<>();
        studentMariusCourses.add(courseBazeDeDate);
        studentMariusCourses.add(courseStructuriDeDate);
        studentMariusCourses.add(courseAlgebra);

        studentMarius.setEnrolledCourses(studentMariusCourses);

        List<Student> courseStructuriDeDateStudents = new ArrayList<>();
        courseStructuriDeDateStudents.add(studentAndrei);
        courseStructuriDeDateStudents.add(studentCodrut);

        List<Course> studentAndreiCourses = new ArrayList<>();
        studentAndreiCourses.add(courseStructuriDeDate);
        studentAndreiCourses.add(courseAlgebra);
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

        courses.add(courseBazeDeDate);
        courses.add(courseStructuriDeDate);
        courses.add(courseAlgebra);

        teachers.add(teacherDor);
        teachers.add(teacherDorel);
    }

    @Test
    public void repairJSON() throws IOException{
        testToCourseJSON();
        testToStudentJSON();
        testToTeacherJSON();
    }

    /**
     * Test for toString() methods
     * of Teacher, Student, Course Objects
     */
    @Test
    public void printAll() {
        System.out.println(studentRazvan.toString());
        System.out.println(studentMarius.toString());
        System.out.println(studentAndrei.toString());
        System.out.println(studentCodrut.toString());
    }


    @Test
    public void testToCourseJSON() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        String serializedCourse = "";

        for (Course c : courses) {

            objectMapper.registerModules(new SimpleModule().addSerializer(Course.class, new CourseSerializer()));

            System.out.println(objectMapper.writeValueAsString(c));

            serializedCourse += objectMapper.writeValueAsString(c);

            serializedCourse += ",";

            writer.writeValue(new File("courseData.json"), serializedCourse);
        }
    }

    @Test
    public void testToStudentJSON() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        String serializedStudent = "";

        for (Student c : students) {

            objectMapper.registerModules(new SimpleModule().addSerializer(Student.class, new StudentSerializer()));

            System.out.println(objectMapper.writeValueAsString(c));

            serializedStudent += objectMapper.writeValueAsString(c);

            serializedStudent += ",";

            writer.writeValue(new File("studentData.json"), serializedStudent);
        }
    }

    @Test
    public void testToTeacherJSON() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        String serializedTeacher = "";

        for (Teacher c : teachers) {

            objectMapper.registerModules(new SimpleModule().addSerializer(Teacher.class, new TeacherSerializer()));

            System.out.println(objectMapper.writeValueAsString(c));

            serializedTeacher += objectMapper.writeValueAsString(c);

            serializedTeacher += ",";

            writer.writeValue(new File("TeacherData.json"), serializedTeacher);
        }
    }



    /*
    @Test
    public void write_date() throws IOException {
        for (Student s:
             students) {
            s.setEnrolledCourses(new ArrayList<>());
        }
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        writer.writeValue(new File("data.json"), students);
    }

     */


}

   /* @Test
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


        }
        reader.close();
    }*/


