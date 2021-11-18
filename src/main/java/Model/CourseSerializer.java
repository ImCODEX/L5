package Model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * JSON Serializer for Course
 * used for storing Courses
 * in JSON format
 */
public class CourseSerializer extends JsonSerializer<Course> {
    @Override
    public void serialize(Course course, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", course.getId());
        jsonGenerator.writeStringField("name", course.getName());
        jsonGenerator.writeNumberField("maxEnrollment", course.getMaxEnrollment());
        jsonGenerator.writeNumberField("credits", course.getCredits());

        jsonGenerator.writeEndObject();
    }
}
