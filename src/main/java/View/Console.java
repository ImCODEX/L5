package View;

import Controller.Controller;
import CustomExceptions.CustomExceptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * UI
 * Main method runs the user interface
 */
public class Console {
    private final Controller controller;

    public Console(Controller controller) {
        this.controller = controller;
    }

    public void showCourses() {
        controller.getCourses().forEach(System.out::println);
    }

    public void showStudents() {
        controller.getStudents().forEach(System.out::println);
    }

    public void showTeachers() {
        controller.getTeacher().forEach(System.out::println);
    }

    public void showSortStudentsByEnrolledCourses() {
        controller.sortStudentsByEnrolledCourses().forEach(System.out::println);
    }

    public void showSortCourseByCredits() {
        controller.sortCourseByCredits().forEach(System.out::println);
    }

    public void updateCourse() throws CustomExceptions, IOException{
        Scanner scanner = new Scanner(System.in);
        showCourses();
        System.out.println("Enter id of Course you want to update:");
        try{
            Integer oldId = scanner.nextInt();
            System.out.println("Now you will need to write a few things that represent Course attributes.");
            System.out.println("Enter Course id:");
            Integer id = scanner.nextInt();
            System.out.println("Enter Course name:");
            scanner.nextLine(); //throw away the \n not consumed by nextInt()
            String name = scanner.nextLine();
            System.out.println("Enter maximum number of students that can be enrolled:");
            Integer maxEnrollment = scanner.nextInt();
            System.out.println("Enter Course credits:");
            Integer credits = scanner.nextInt();
            controller.updateCourse(oldId, id, name, maxEnrollment, credits);

        } catch (Exception e) {
            controller.saveInput();
            throw (new CustomExceptions("Invalid Input"));
        }
    }

    public void updateStudent() throws CustomExceptions, IOException{
        Scanner scanner = new Scanner(System.in);
        showStudents();
        System.out.println("Enter id of Student you want to update:");

        try {
            Integer oldId = scanner.nextInt();
            System.out.println("You will need to write a few things that represent Student attributes.");
            System.out.println("Enter Student id:");
            int id = scanner.nextInt();
            System.out.println("Enter Student first name:");
            scanner.nextLine(); //throw away the \n not consumed by nextInt()
            String firstName = scanner.nextLine();
            System.out.println("Enter Student last name:");
            String lastName = scanner.nextLine();
            System.out.println("Please input how many courses do you wish the Student to be enrolled: ");
            int coursesCount = scanner.nextInt();
            List<Integer> coursesIds = new ArrayList<>();
            showCourses();
            for (int i = 0; i < coursesCount; i++) {
                System.out.println("Select the Course(s) by entering Course id:");
                Integer courseId = scanner.nextInt();
                coursesIds.add(courseId);
            }
            controller.updateStudent(oldId, firstName, lastName, id, 0, coursesIds);

        } catch (Exception e) {
            controller.saveInput();
            throw (new CustomExceptions("Invalid Input"));
        }

    }

    public void updateTeacher() throws CustomExceptions, IOException {
        Scanner scanner = new Scanner(System.in);
        showTeachers();
        System.out.println("Enter id of Teacher you want to update:");
        try {
            Integer oldId = scanner.nextInt();
            System.out.println("You will need to write a few things that represent Teacher attributes.");
            System.out.println("Enter Teacher id:");
            int id = scanner.nextInt();
            System.out.println("Enter Teacher first name:");
            scanner.nextLine(); //throw away the \n not consumed by nextInt()
            String firstName = scanner.nextLine();
            System.out.println("Enter Teacher last name:");
            String lastName = scanner.nextLine();
            System.out.println("Please input how many courses do you wish the Teacher to teach: ");
            int coursesCount = scanner.nextInt();
            List<Integer> coursesIds = new ArrayList<>();
            showCourses();
            for (int i = 0; i < coursesCount; i++) {
                System.out.println("Select the Course(s) by entering Course id:");
                Integer courseId = scanner.nextInt();
                coursesIds.add(courseId);
            }
            controller.updateTeacher(oldId, firstName, lastName, id, coursesIds);
        } catch (Exception e) {
            controller.saveInput();
            throw (new CustomExceptions("Invalid Input"));
        }

    }

    public void addCourse() throws CustomExceptions, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You will need to write a few things that represent Course attributes.");
        System.out.println("Enter Course id:");
        try {
            Integer id = scanner.nextInt();
            System.out.println("Enter Course name:");
            scanner.nextLine(); //throw away the \n not consumed by nextInt()
            String name = scanner.nextLine();
            System.out.println("Enter maximum number of students that can be enrolled:");
            Integer maxEnrollment = scanner.nextInt();
            System.out.println("Enter Course credits:");
            Integer credits = scanner.nextInt();
            controller.addCourse(id, name, maxEnrollment, credits);
        } catch (Exception e) {
            controller.saveInput();
            throw (new CustomExceptions("Invalid Input"));
        }

    }

    public void addStudent() throws CustomExceptions, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You will need to write a few things that represent Student attributes.");

        try {
            System.out.println("Enter Student id:");
            int id = scanner.nextInt();
            System.out.println("Enter Student first name:");
            scanner.nextLine(); //throw away the \n not consumed by nextInt()
            String firstName = scanner.nextLine();
            System.out.println("Enter Student last name:");
            String lastName = scanner.nextLine();
            System.out.println("Please input how many courses do you wish the Student to be enrolled: ");
            int coursesCount = scanner.nextInt();
            List<Integer> coursesIds = new ArrayList<>();
            showCourses();
            for (int i = 0; i < coursesCount; i++) {
                System.out.println("Select the Course(s) by entering Course id:");
                Integer courseId = scanner.nextInt();
                coursesIds.add(courseId);
            }
            controller.addStudent(firstName, lastName, id, 0, coursesIds);

        } catch (Exception e) {
            controller.saveInput();
            throw (new CustomExceptions("Invalid Input"));
        }
    }

    public void addTeacher() throws CustomExceptions, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You will need to write a few things that represent Teacher attributes.");

        try {
            System.out.println("Enter Teacher id:");
            int id = scanner.nextInt();
            System.out.println("Enter Teacher first name:");
            scanner.nextLine(); //throw away the \n not consumed by nextInt()
            String firstName = scanner.nextLine();
            System.out.println("Enter Teacher last name:");
            String lastName = scanner.nextLine();
            System.out.println("Please input how many courses do you wish the Teacher to teach: ");
            int coursesCount = scanner.nextInt();
            List<Integer> coursesIds = new ArrayList<>();
            showCourses();
            for (int i = 0; i < coursesCount; i++) {
                System.out.println("Select the Course(s) by entering Course id:");
                Integer courseId = scanner.nextInt();
                coursesIds.add(courseId);
            }
            controller.addTeacher(firstName, lastName, id, coursesIds);
        } catch (Exception e) {
            controller.saveInput();
            throw (new CustomExceptions("Invalid Input"));
        }
    }

    public void deleteCourse() throws IOException, CustomExceptions {
        Scanner scanner = new Scanner(System.in);
        showCourses();
        System.out.println("ID of Course you want to delete:");
        try{
            int id = scanner.nextInt();
            controller.deleteCourse(controller.findCourseById(id));
        } catch (Exception e) {
            controller.saveInput();
            throw (new CustomExceptions("Invalid Input"));
        }

    }

    public void deleteStudent() throws  IOException, CustomExceptions{
        Scanner scanner = new Scanner(System.in);
        showStudents();
        System.out.println("ID of Student you want to delete:");
        try{
            int id = scanner.nextInt();
            controller.deleteStudent(controller.findStudentById(id));
        } catch (Exception e) {
            controller.saveInput();
            throw (new CustomExceptions("Invalid Input"));
        }
    }

    public void deleteTeacher() throws  IOException, CustomExceptions{
        Scanner scanner = new Scanner(System.in);
        showTeachers();
        System.out.println("ID of Teacher you want to delete:");
        try{
            int id = scanner.nextInt();
            controller.deleteTeacher(controller.findTeacherById(id));
        } catch (Exception e) {
            controller.saveInput();
            throw (new CustomExceptions("Invalid Input"));
        }
    }

    public void showFilterStudentsByLessThenXCourses() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Students with less or equal then X enrolled courses will be show.");
        System.out.println("Insert X value: ");
        int temp = scanner.nextInt();
        controller.filterStudentsByLessThenXCourses(temp).forEach(System.out::println);
    }

    public void showFilterCourseByMaxEnrollment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Courses with less or equal then X maximum enrollment will be show.");
        System.out.println("Insert X value: ");
        int temp = scanner.nextInt();
        controller.filterCourseByMaxEnrollment(temp).forEach(System.out::println);
    }

    public void run() throws CustomExceptions, IOException {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("""
                     
                     0. Exit Program \r
                     1. Show all Courses \r
                     2. Show all Students \r
                     3. Show all Teachers \r
                     4. Add a Course \r
                     5. Add a Student \r
                     6. Add a Teacher \r
                     7. Delete a Course \r
                     8. Delete a Student \r
                     9. Delete a Teacher \r
                     10. Update a Course \r
                     11. Update a Student \r
                     12. Update a Teacher \r
                     13. Sort students by most enrolled Student \r
                     14. Sort courses by credits \r
                     15. Filter Students by less or equal then a given number of enrollments\s
                     16. Filter Courses by less or equal then a given number of enrollments\s
                    """);
            System.out.println("Enter input: ");
            int variant = scanner.nextInt();
            System.out.println("You've entered: " + variant);
            if (variant == 0)
                break;
            else if (variant == 1)
                showCourses();
            else if (variant == 2)
                showStudents();
            else if (variant == 3)
                showTeachers();
            else if (variant == 4)
                addCourse();
            else if (variant == 5)
                addStudent();
            else if (variant == 6)
                addTeacher();
            else if (variant == 7)
                deleteCourse();
            else if (variant == 8)
                deleteStudent();
            else if (variant == 9)
                deleteTeacher();
            else if (variant == 10)
                updateCourse();
            else if (variant == 11)
                updateStudent();
            else if (variant == 12)
                updateTeacher();
            else if (variant == 13)
                showSortStudentsByEnrolledCourses();
            else if (variant == 14)
                showSortCourseByCredits();
            else if (variant == 15) {
                showFilterStudentsByLessThenXCourses();
            } else if (variant == 16) {
                showFilterCourseByMaxEnrollment();
            } else
                throw (new CustomExceptions("Invalid input!"));
        }
    }
}
