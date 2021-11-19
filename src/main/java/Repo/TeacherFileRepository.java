package Repo;

import CustomExceptions.CustomExceptions;
import Model.*;
import Model.Teacher;
import Model.TeacherSerializer;
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
 * FileRepository for Teacher
 * Automatically reads from "TeacherData.json" when instantiated
 * and stores Teachers in memory
 */
public class TeacherFileRepository extends FileRepository<Teacher> {
    private CourseFileRepository courseFileRepository;

    public TeacherFileRepository(CourseFileRepository courseFileRepository_) throws IOException {
        super();
        courseFileRepository = courseFileRepository_;
    }

    public void run() throws IOException {
        BufferedReader tempReader = new BufferedReader(new FileReader("TeacherData.json"));

        String line = tempReader.readLine().replace("\\","");
        tempReader.close();

        StringBuilder stringBuilder = new StringBuilder(line);

        stringBuilder.replace(0,1,"[");
        stringBuilder.replace(line.length()-2,line.length(),"]");

        BufferedWriter tempWriter = new BufferedWriter(new FileWriter("TeacherData.json"));

        tempWriter.write(stringBuilder.toString());
        tempWriter.close();

        Reader reader = new BufferedReader(new FileReader("TeacherData.json"));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for (JsonNode n: parser){
            Teacher t = new Teacher();

            t.setTeacherId(n.path("teacherId").asInt());
            t.setFirstName(n.path("firstName").asText());
            t.setLastName(n.path("lastName").asText());

            String courses = n.path("courses").asText();
            List<String> splits = Arrays.asList(courses.replace("[","").replace("]","").replace(" ","").split(","));
            List<Course> courseList = new ArrayList<>();

            if (!splits.get(0).equals("")) {
                List<Integer> coursesID = new ArrayList<>(splits.stream().map(Integer::valueOf).toList());

                for (Course c :
                        courseFileRepository.repoList) {
                    for (Integer cID :
                            coursesID) {
                        if (cID == c.getId())
                            courseList.add(c);
                    }
                }
            }

            t.setCourses(courseList);
            for (Course c:
                    courseList) {
                c.setTeacher(t);
            }
            repoList.add(t);
        }
        reader.close();
    }

    /**
     * TeacherFileRepository destructor method
     * automatically called for now in constructor
     * since there is no method for the user to
     * insert objects in the UI
     * @throws IOException
     */
    public void close() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        String serializedTeacher = "";

        for(Teacher c : repoList) {

            objectMapper.registerModules(new SimpleModule().addSerializer(Teacher.class, new TeacherSerializer()));

            serializedTeacher += objectMapper.writeValueAsString(c);

            serializedTeacher += ",";

            writer.writeValue(new File("TeacherData.json"), serializedTeacher);
        }
    }

    @Override
    public Teacher find(Integer id) throws CustomExceptions {
        for (Teacher t:
             repoList) {
            if(t.getTeacherId() == id)
                return t;
        }
        throw (new CustomExceptions("Teacher not found!"));
    }
}
