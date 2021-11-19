package Repo;

import CustomExceptions.CustomExceptions;
import Model.Course;
import Model.CourseSerializer;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.*;

/**
 * FileRepository for Course
 * Automatically reads from "courseData.json" when instantiated
 * and stores Courses in memory
 */
public class CourseFileRepository extends FileRepository<Course> {

    /**
     * CourseFileRepository
     * reads from "courseData.json"
     * @throws IOException
     */
    public CourseFileRepository(){
        super();
    }

    public void run() throws IOException{
        BufferedReader tempReader = new BufferedReader(new FileReader("courseData.json"));

        String line = tempReader.readLine().replace("\\","");
        tempReader.close();

        StringBuilder stringBuilder = new StringBuilder(line);

        stringBuilder.replace(0,1,"[");
        stringBuilder.replace(line.length()-2,line.length(),"]");

        BufferedWriter tempWriter = new BufferedWriter(new FileWriter("courseData.json"));

        tempWriter.write(stringBuilder.toString());
        tempWriter.close();

        Reader reader = new BufferedReader(new FileReader("courseData.json"));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for (JsonNode n: parser){
            Course c = new Course();

            c.setId(n.path("id").asInt());
            c.setName(n.path("name").asText());
            c.setMaxEnrollment(n.path("maxEnrollment").asInt());
            c.setCredits(n.path("credits").asInt());

            repoList.add(c);
        }
        reader.close();
    }


    /**
     * CourseFileRepository destructor method
     * automatically called for now in constructor
     * since there is no method for the user to
     * insert objects in the UI
     * @throws IOException
     */
    public void close() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());

        String serializedCourse = "";

        for(Course c : repoList) {

            objectMapper.registerModules(new SimpleModule().addSerializer(Course.class, new CourseSerializer()));

            serializedCourse += objectMapper.writeValueAsString(c);

            serializedCourse += ",";

            writer.writeValue(new File("courseData.json"), serializedCourse);
        }
    }

    @Override
    public Course find(Integer id) throws CustomExceptions {
        for (Course c:
                repoList) {
            if(c.getId() == id)
                return c;
        }
        throw(new CustomExceptions("Course not found!"));
    }
}
