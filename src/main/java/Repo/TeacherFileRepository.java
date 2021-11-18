package Repo;

import Model.*;
import Model.Teacher;
import Model.TeacherSerializer;
import Repo.CourseFileRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TeacherFileRepository extends  InMemoryRepository<Teacher>{
    private CourseFileRepository courseFileRepository;

    public TeacherFileRepository(CourseFileRepository courseFileRepository_) throws IOException {
        super();
        courseFileRepository = courseFileRepository_;

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
            String[] splits =  courses.replace("[","").replace("]","").replace(" ","").split(",");
            List<Integer> coursesID = new ArrayList<>(Arrays.stream(splits).map(Integer::valueOf).toList());

            List<Course> courseList = new ArrayList<>();
            for (Course c:
                    courseFileRepository.repoList) {
                for (Integer cID:
                        coursesID) {
                    if(cID == c.getId())
                        courseList.add(c);
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
        close();
}

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
}
