package View;

import Controller.Controller;
import CustomExceptions.CustomExceptions;
import Repo.CourseFileRepository;
import Repo.StudentFileRepository;
import Repo.TeacherFileRepository;

import java.io.IOException;
import java.util.Scanner;



public class Console {
    public static void main(String[] args) throws IOException, CustomExceptions {
        Scanner scanner = new Scanner(System.in);
        CourseFileRepository courseFileRepository = new CourseFileRepository();
        StudentFileRepository studentFileRepository = new StudentFileRepository(courseFileRepository);
        TeacherFileRepository teacherFileRepository = new TeacherFileRepository(courseFileRepository);
        Controller controller = new Controller(courseFileRepository, studentFileRepository, teacherFileRepository);

        System.out.println(" 1. Show all Courses \r\n 2. Show all Students \r\n 3. Show all Teachers \r\n " +
                "4. Sort students by most enrolled Student \r\n 5. Sort courses by credits \r\n " +
                "6. Filter Students by less or equal then a given number of enrollments \n" +
                " 7. Filter Courses by less or equal then a given number of enrollments \n");
        System.out.println("Enter input: ");
        int variant = scanner.nextInt();
        System.out.println("You've entered: " + variant);
        if (variant == 1)
            System.out.println(courseFileRepository.getAll());
        else if (variant == 2)
            System.out.println(studentFileRepository.getAll());
        else if (variant == 3)
            System.out.println(teacherFileRepository.getAll());
        else if (variant == 4)
            controller.sortStudentsByEnrolledCourses().forEach(System.out::println);
        else if (variant == 5)
            controller.sortCourseByCredits().forEach(System.out::println);
        else if (variant == 6) {
            System.out.println("Students with less then X enrolled courses will be show.");
            System.out.println("Insert X value: ");
            int temp = scanner.nextInt();
            controller.filterStudentsByLessThenXCourses(temp).forEach(System.out::println);
        } else if (variant == 7) {
            System.out.println("Courses with less then X maximum enrollment will be show.");
            System.out.println("Insert X value: ");
            int temp = scanner.nextInt();
            controller.filterCourseByMaxEnrollment(temp).forEach(System.out::println);
        } else
            throw (new CustomExceptions("Invalid input!"));


    }
}
