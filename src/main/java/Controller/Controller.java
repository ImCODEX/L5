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

class MyComparator implements java.util.Comparator<Student>{

    public MyComparator(){
        super();
    }

    public int compare(Student s1, Student s2){
        int l1 = s1.getEnrolledCourses().size();
        int l2 = s2.getEnrolledCourses().size();
        return l1 - l2;
    }
}

public class Controller {
    private CourseFileRepository courseFileRepository;
    private StudentFileRepository studentFileRepository;
    private TeacherFileRepository teacherFileRepository;

    public Controller(CourseFileRepository courseFileRepository, StudentFileRepository studentFileRepository, TeacherFileRepository teacherFileRepository) {
        this.courseFileRepository = courseFileRepository;
        this.teacherFileRepository = teacherFileRepository;
        this.studentFileRepository = studentFileRepository;

    }

    public List<Course> getCourses(){
        return courseFileRepository.getAll();
    }

    public List<Student> getStudents(){
        return studentFileRepository.getAll();
    }

    public List<Teacher> getTeacher(){
        return teacherFileRepository.getAll();
    }

    public List<Student> sortStudentsByCourseId(){
        return studentFileRepository.getAll().stream().sorted((x, y) -> (int) (x.getStudentId() - y.getStudentId())).collect(Collectors.toList());
    }

    public List<Student> sortStudentsByEnrolledCourses(){
        return studentFileRepository.getAll().stream().sorted((x, y) -> (y.getEnrolledCourses().size() - x.getEnrolledCourses().size())).collect(Collectors.toList());
    }

    public List<Course> sortCourseByCredits(){
        return courseFileRepository.getAll().stream().sorted((x, y) -> (y.getCredits() - x.getCredits())).collect(Collectors.toList());
    }

    public List<Student> filterStudentsByLessThenXCourses(Integer coursesCount){
        return studentFileRepository.getAll().stream().filter(x -> x.getEnrolledCourses().size() <= coursesCount).collect(Collectors.toList());
    }

    public List<Course> filterCourseByMaxEnrollment(Integer enrollmentCount){
        return courseFileRepository.getAll().stream().filter(x -> x.getMaxEnrollment() <= enrollmentCount).collect(Collectors.toList());
    }

}
