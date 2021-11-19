package Repo;

import CustomExceptions.CustomExceptions;
import Model.Course;
import Model.Student;
import Model.StudentSerializer;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FileRepository for Student
 * Automatically reads from "studentData.json" when instantiated
 * and stores Students in memory
 */
public class StudentFileRepository extends FileRepository<Student> {
    private CourseFileRepository courseFileRepository;

    public StudentFileRepository(CourseFileRepository courseFileRepository_) {
        super();
        courseFileRepository = courseFileRepository_;
    }

    public void run() throws IOException {

        BufferedReader tempReader = new BufferedReader(new FileReader("studentData.json"));

        String line = tempReader.readLine().replace("\\", "");
        tempReader.close();

        StringBuilder stringBuilder = new StringBuilder(line);

        stringBuilder.replace(0, 1, "[");
        stringBuilder.replace(line.length() - 2, line.length(), "]");

        BufferedWriter tempWriter = new BufferedWriter(new FileWriter("studentData.json"));

        tempWriter.write(stringBuilder.toString());
        tempWriter.close();

        Reader reader = new BufferedReader(new FileReader("studentData.json"));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for (JsonNode n : parser) {
            Student s = new Student();

            s.setStudentId(n.path("studentId").asInt());
            s.setFirstName(n.path("firstName").asText());
            s.setLastName(n.path("lastName").asText());
            s.setTotalCredits(n.path("totalCredits").asInt());


            String courses = n.path("enrolledCourses").asText();
            List<String> splits = Arrays.asList(courses.replace("[", "").replace("]", "").replace(" ", "").split(","));
            List<Course> courseList = new ArrayList<>();
            if (!splits.get(0).equals("")) {
                List<Integer> coursesID = new ArrayList<>(splits).stream().map(Integer::valueOf).toList();

                for (Course c :
                        courseFileRepository.repoList) {
                    for (Integer cID :
                            coursesID) {
                        if (cID == c.getId())
                            courseList.add(c);
                    }
                }

            }
            s.setEnrolledCourses(courseList);
            repoList.add(s);
            for (Course c :
                    courseList) {
                if (c.getStudentsEnrolled() == null)
                    c.setStudentsEnrolled(new ArrayList<>());
                c.enrollStudent(s);
            }
        }
        reader.close();
    }

    /**
     * StudentFileRepository destructor method
     * automatically called for now in constructor
     * since there is no method for the user to
     * insert objects in the UI
     *
     * @throws IOException
     */
    public void close() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        String serializedStudent = "";

        for (Student s : repoList) {

            objectMapper.registerModules(new SimpleModule().addSerializer(Student.class, new StudentSerializer()));

            serializedStudent += objectMapper.writeValueAsString(s);

            serializedStudent += ",";

            writer.writeValue(new File("StudentData.json"), serializedStudent);
        }
    }

    @Override
    public Student find(Integer id) throws CustomExceptions {
        for (Student s :
                repoList)
            if (s.getStudentId() == id)
                return s;
        throw (new CustomExceptions("Student not found!"));

    }
}
