import Model.Course;
import Model.Student;
import Model.Teacher;
import Repo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FileRepoTest {
    private StudentFileRepository studentFileRepository;
    private TeacherFileRepository teacherFileRepository;
    private CourseFileRepository courseFileRepository;

    @BeforeEach
    public void setup() throws IOException {
        courseFileRepository = new CourseFileRepository();
        teacherFileRepository = new TeacherFileRepository(courseFileRepository);
        studentFileRepository = new StudentFileRepository(courseFileRepository);

    }

    @Test
    public void printAll() throws IOException {

        for (Student s:
             studentFileRepository.getAll()) {
            System.out.println(s.toString());
        }

        for (Teacher t:
                teacherFileRepository.getAll()) {
            System.out.println(t.toString());
        }

        for (Course c:
                courseFileRepository.getAll()) {
            System.out.println(c.toString());
        }

    }

}
