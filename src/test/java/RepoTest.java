/*
import Repo.CourseRepository;
import Repo.StudentRepository;
import Repo.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import Model.Course;
import Model.Student;
import Model.Teacher;
import Repo.InMemoryRepository;

*/
/**
 * Test Class for Repositories
 *//*


public class RepoTest {
    private InMemoryRepository<Student> studentInMemoryRepository;
    private InMemoryRepository<Teacher> teacherInMemoryRepository;
    private InMemoryRepository<Course> courseInMemoryRepository;

    */
/**
     * Setup method
     * instantiates Student, Teacher, Course Repositories
     * and adds 1 Object of relevant type to each one of
     * the repositories
     *//*

    @BeforeEach
    public void setup(){
        studentInMemoryRepository = new StudentRepository();
        teacherInMemoryRepository = new TeacherRepository();
        courseInMemoryRepository = new CourseRepository();

        List<Student> students = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();
        List<Course> courses = new ArrayList<>();

        studentInMemoryRepository.add(new Student("Popescu", "Ion", 11111, 0, courses));
        teacherInMemoryRepository.add(new Teacher("Vasile", "Ilie", 1, courses));
        courseInMemoryRepository.add(new Course(3,"FP",teacherInMemoryRepository.getAll().get(0), 10, students, 5));


    }

    */
/**
     * Test for add Method
     *//*

    @Test
    public void testAdd(){
        assertEquals(studentInMemoryRepository.getAll().size(), 1);
        assertEquals(teacherInMemoryRepository.getAll().size(), 1);
        assertEquals(courseInMemoryRepository.getAll().size(), 1);

        List<Student> students = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();
        List<Course> courses = new ArrayList<>();

        assertEquals(studentInMemoryRepository.getAll().get(0), new Student("Popescu", "Ion", 11111, 0, courses));
        assertEquals(teacherInMemoryRepository.getAll().get(0), new Teacher("Vasile", "Ilie", 1, courses));
        assertEquals(courseInMemoryRepository.getAll().get(0), new Course(6,"FP",teacherInMemoryRepository.getAll().get(0), 10, students, 5));
    }

    */
/**
     * Test for delete method
     *//*

    @Test
    public void testDelete(){
        studentInMemoryRepository.delete(studentInMemoryRepository.getAll().get(0));
        teacherInMemoryRepository.delete(teacherInMemoryRepository.getAll().get(0));
        courseInMemoryRepository.delete((courseInMemoryRepository.getAll().get(0)));

        assertEquals(studentInMemoryRepository.getAll().size(), 0);
        assertEquals(teacherInMemoryRepository.getAll().size(), 0);
        assertEquals(courseInMemoryRepository.getAll().size(), 0);

    }

    */
/**
     * Test for update Method
     *//*

    @Test
    public void testUpdate(){
        List<Student> students = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();
        List<Course> courses = new ArrayList<>();

        studentInMemoryRepository.update(studentInMemoryRepository.getAll().get(0), new Student("SuperPopescu", "SuperIon", 99999, 0, courses));
        teacherInMemoryRepository.update(teacherInMemoryRepository.getAll().get(0), new Teacher("SuperVasile", "SuperIlie", 1, courses));
        courseInMemoryRepository.update(courseInMemoryRepository.getAll().get(0), new Course(4,"FP",teacherInMemoryRepository.getAll().get(0), 10, students, 5));

        assertEquals(studentInMemoryRepository.getAll().get(0), new Student("SuperPopescu", "SuperIon", 99999, 0, courses));
        assertNotEquals(teacherInMemoryRepository.getAll().get(0), new Teacher("Vasile", "Ilie", 1, courses));
        assertEquals(courseInMemoryRepository.getAll().get(0), new Course(5,"FP",teacherInMemoryRepository.getAll().get(0), 10, students, 5));

        assertEquals(studentInMemoryRepository.getAll().size(), 1);
        assertEquals(teacherInMemoryRepository.getAll().size(), 1);
        assertEquals(courseInMemoryRepository.getAll().size(), 1);
    }

    */
/**
     * Test for getAll/Read Method
     *//*

    @Test
    public void testRead(){
        List<Course> courses = new ArrayList<>();
        assertEquals(studentInMemoryRepository.getAll().size(), 1);
        studentInMemoryRepository.add(new Student("Popescu", "Ion", 11111, 0, courses));
        assertEquals(studentInMemoryRepository.getAll().size(), 2);
        studentInMemoryRepository.delete(new Student("Popescu2", "Ion", 11110, 0, courses)); //Student Popescu2 Ion doesn't exist so it won't delete anything
        assertEquals(studentInMemoryRepository.getAll().size(), 2);
    }


}
*/
