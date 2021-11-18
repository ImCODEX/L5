package Model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * JSON Serializer for Teachers
 * used for storing Teachers
 * in JSON format
 */
public class TeacherSerializer extends JsonSerializer<Teacher> {


    @Override
    public void serialize(Teacher teacher, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("teacherId", teacher.getTeacherId());
        jsonGenerator.writeStringField("firstName", teacher.getFirstName());
        jsonGenerator.writeStringField("lastName", teacher.getLastName());
        List<Integer> idList = new ArrayList<>();
        for(Course c : teacher.getCourses())
            idList.add(c.getId());
        jsonGenerator.writeStringField("courses", String.valueOf(idList));

        jsonGenerator.writeEndObject();
    }
}
