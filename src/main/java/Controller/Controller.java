package Controller;

import Model.Course;
import Model.Student;
import Model.Teacher;
import Repo.CourseFileRepository;
import Repo.StudentFileRepository;
import Repo.TeacherFileRepository;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller Class
 */
public class Controller {
    private CourseFileRepository courseFileRepository;
    private StudentFileRepository studentFileRepository;
    private TeacherFileRepository teacherFileRepository;

    /**
     * Controller makes use of 3 FileRepositories
     * @param courseFileRepository:
     * @param studentFileRepository:
     * @param teacherFileRepository:
     */
    public Controller(CourseFileRepository courseFileRepository, StudentFileRepository studentFileRepository, TeacherFileRepository teacherFileRepository) {
        this.courseFileRepository = courseFileRepository;
        this.teacherFileRepository = teacherFileRepository;
        this.studentFileRepository = studentFileRepository;

    }

    /**
     * Getter for all courses
     * @return: List<Course>
     */
    public List<Course> getCourses(){
        return courseFileRepository.getAll();
    }

    /**
     * Getter for all students
     * @return: List<Student>
     */
    public List<Student> getStudents(){
        return studentFileRepository.getAll();
    }

    /**
     * Getter for all teachers
     * @return: List<Teacher>
     */
    public List<Teacher> getTeacher(){
        return teacherFileRepository.getAll();
    }

    /**
     * Sort students descending by
     * ammount of enrolled courses
     * @return: List<Student>
     */
    public List<Student> sortStudentsByEnrolledCourses(){
        return studentFileRepository.getAll().stream().sorted((x, y) -> (y.getEnrolledCourses().size() - x.getEnrolledCourses().size())).collect(Collectors.toList());
    }

    /**
     * Sort courses descending by
     * amount of credits
     * @return: List<Course>
     */
    public List<Course> sortCourseByCredits(){
        return courseFileRepository.getAll().stream().sorted((x, y) -> (y.getCredits() - x.getCredits())).collect(Collectors.toList());
    }

    /**
     * Filter students by
     * max amount of enrolled Courses
     * @param coursesCount: user given integer for filter
     * @return: List<Student>
     */
    public List<Student> filterStudentsByLessThenXCourses(Integer coursesCount){
        return studentFileRepository.getAll().stream().filter(x -> x.getEnrolledCourses().size() <= coursesCount).collect(Collectors.toList());
    }

    /**
     * Filter courses by
     * max amount of enrollment
     * @param enrollmentCount: user given integer for filter
     * @return: List<Course>
     */
    public List<Course> filterCourseByMaxEnrollment(Integer enrollmentCount){
        return courseFileRepository.getAll().stream().filter(x -> x.getMaxEnrollment() <= enrollmentCount).collect(Collectors.toList());
    }

}
