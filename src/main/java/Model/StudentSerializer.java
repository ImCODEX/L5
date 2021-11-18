package Model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * JSON Serializer for Students
 * used for storing Students
 * in JSON format
 */
public class StudentSerializer extends JsonSerializer<Student> {


    @Override
    public void serialize(Student student, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("studentId", student.getStudentId());
        jsonGenerator.writeStringField("firstName", student.getFirstName());
        jsonGenerator.writeStringField("lastName", student.getLastName());
        jsonGenerator.writeNumberField("totalCredits", student.getTotalCredits());
        List<Integer> idList = new ArrayList<>();
        for(Course c : student.getEnrolledCourses())
            idList.add(c.getId());
        jsonGenerator.writeStringField("enrolledCourses", String.valueOf(idList));

        jsonGenerator.writeEndObject();
    }
}
