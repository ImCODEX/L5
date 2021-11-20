package Main;

import Controller.Controller;
import CustomExceptions.CustomExceptions;
import Repo.CourseFileRepository;
import Repo.StudentFileRepository;
import Repo.TeacherFileRepository;
import View.Console;

import java.io.IOException;

/**
 * Main Class
 */
public class Main {

    /**
     * Main Methods
     * Initialises all logic
     * @throws IOException in case of wrong JSON read format
     * @throws CustomExceptions in care of different in-build Exceptions
     */
    public static void main(String[] args) throws IOException, CustomExceptions {
        CourseFileRepository courseFileRepository = new CourseFileRepository();
        StudentFileRepository studentFileRepository = new StudentFileRepository(courseFileRepository);
        TeacherFileRepository teacherFileRepository = new TeacherFileRepository(courseFileRepository);
        Controller controller = new Controller(courseFileRepository, studentFileRepository, teacherFileRepository);
        controller.startInput();
        Console console = new Console(controller);
        console.run();
        controller.saveInput();
    }
}