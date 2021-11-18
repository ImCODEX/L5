package Model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

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
