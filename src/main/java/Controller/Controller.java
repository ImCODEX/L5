package Controller;

import CustomExceptions.CustomExceptions;
import Model.Course;
import Model.Student;
import Model.Teacher;
import Repo.CourseFileRepository;
import Repo.StudentFileRepository;
import Repo.TeacherFileRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Controller Class
 */
public class Controller {
    private final CourseFileRepository courseFileRepository;
    private final StudentFileRepository studentFileRepository;
    private final TeacherFileRepository teacherFileRepository;

    /**
     * Controller makes use of 3 FileRepositories
     *
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
     * Read Method
     */
    public void startInput() throws IOException {
        courseFileRepository.run();
        teacherFileRepository.run();
        studentFileRepository.run();
    }

    /**
     * Destructor/Save Method that has to been run
     * before closing the program in order to save
     * objects in consistent format
     */
    public void saveInput() throws IOException {
        courseFileRepository.close();
        teacherFileRepository.close();
        studentFileRepository.close();
    }

    /**
     * Getter for all courses
     *
     * @return: List<Course>
     */
    public List<Course> getCourses() {
        return courseFileRepository.getAll();
    }

    /**
     * Getter for all students
     *
     * @return: List<Student>
     */
    public List<Student> getStudents() {
        return studentFileRepository.getAll();
    }

    /**
     * Getter for all teachers
     *
     * @return: List<Teacher>
     */
    public List<Teacher> getTeacher() {
        return teacherFileRepository.getAll();
    }

    public Course addCourse(int id, String name, int maxEnrollment, int credits) {
        return courseFileRepository.add(new Course(id, name, maxEnrollment, credits));
    }

    /**
     * Student add Method
     * @param firstName: String
     * @param lastName: String
     * @param studentId_: Int
     * @param totalCredits_: Int
     * @param coursesId: List<Integer>
     * @return Student
     */
    public Student addStudent(String firstName, String lastName, long studentId_, int totalCredits_, List<Integer> coursesId) {
        List<Course> tempCourses = new ArrayList<>();
        for (Integer id :
                coursesId) {
            for (Course c :
                    courseFileRepository.getAll()) {
                if (c.getId() == id)
                    tempCourses.add(c);
            }
        }
        return studentFileRepository.add(new Student(firstName, lastName, studentId_, totalCredits_, tempCourses));
    }

    /**
     * Teacher add Method
     * @param firstName: String
     * @param lastName: String
     * @param teacherId_: Int
     * @param courses_: List<Integer>
     * @return Teacher
     */
    public Teacher addTeacher(String firstName, String lastName, int teacherId_, List<Integer> courses_){
        List<Course> tempCourses = new ArrayList<>();
        for (Integer id :
                courses_) {
            for (Course c :
                    courseFileRepository.getAll()) {
                if (c.getId() == id)
                    tempCourses.add(c);
            }
        }
        return teacherFileRepository.add(new Teacher(firstName, lastName, teacherId_, tempCourses));
    }

    /**
     * Find by id Method for Course
     * @param id: Int
     * @return Course
     * @throws CustomExceptions in case of not found
     */
    public Course findCourseById(int id) throws CustomExceptions {
        return courseFileRepository.find(id);
    }

    /**
     * Find Student by Id
     * @param id: Int
     * @return Student
     * @throws CustomExceptions in case of not found
     */
    public Student findStudentById(int id) throws CustomExceptions{
        return studentFileRepository.find(id);
    }

    /**
     * Find Teacher by Id
     * @param id: Int
     * @return Teacher
     * @throws CustomExceptions in case of not found
     */
    public Teacher findTeacherById(int id) throws CustomExceptions{
        return teacherFileRepository.find(id);
    }

    /**
     * Delete Course Method
     * @param course: Course
     */
    public void deleteCourse(Course course){
        courseFileRepository.delete(course);
    }

    /**
     * Delete Student Method
     * @param student: Student
     */
    public void deleteStudent(Student student){
        studentFileRepository.delete(student);
    }

    /**
     * Delete Teacher Method
     * @param teacher: Teacher
     */
    public void deleteTeacher(Teacher teacher){
        teacherFileRepository.delete(teacher);
    }

    /**
     * Update Course Method
     * @param oldCourseId: Id of the Course that needs to be updated
     * @param id: New Id
     * @param name: String
     * @param maxEnrollment: Int
     * @param credits: Int
     * @throws CustomExceptions in case of Course not found
     */
    public void updateCourse(int oldCourseId, int id, String name, int maxEnrollment, int credits) throws CustomExceptions {
        Course tempCourse = findCourseById(oldCourseId);
        if(Objects.equals(name, ""))
            name = tempCourse.getName();

        courseFileRepository.update(tempCourse, new Course(id, name, maxEnrollment, credits));
    }

    /**
     * Update Student Method
     * @param oldStudentId: Id of Student that needs to be updated
     * @param firstName: String
     * @param lastName: String
     * @param studentId_: Int
     * @param totalCredits_: Int
     * @param coursesId: List<Integer>
     * @throws CustomExceptions in case of Student not found
     */
    public void updateStudent(long oldStudentId, String firstName, String lastName, long studentId_, int totalCredits_, List<Integer> coursesId) throws CustomExceptions {
        Student tempStudent = findStudentById((int) oldStudentId);
        if(Objects.equals(firstName, ""))
            firstName = tempStudent.getFirstName();
        if(Objects.equals(lastName, ""))
            lastName = tempStudent.getLastName();

        List<Course> tempCourses = new ArrayList<>();
        for (Integer id :
                coursesId) {
            for (Course c :
                    courseFileRepository.getAll()) {
                if (c.getId() == id)
                    tempCourses.add(c);
            }
        }

        studentFileRepository.update(tempStudent, new Student(firstName, lastName, studentId_, totalCredits_, tempCourses));
    }

    /**
     * Update Teacher Method
     * @param oldId: Id of Teacher that needs to be updated
     * @param firstName: String
     * @param lastName: String
     * @param teacherId_: Int
     * @param courses_: List<Integer>
     * @throws CustomExceptions in case of Teacher not found
     */
    public void updateTeacher(int oldId, String firstName, String lastName, int teacherId_, List<Integer> courses_) throws CustomExceptions {
        Teacher tempTeacher = findTeacherById(oldId);
        if (Objects.equals(firstName, ""))
            firstName = tempTeacher.getFirstName();
        if (Objects.equals(lastName, ""))
            lastName = tempTeacher.getLastName();

        List<Course> tempCourses = new ArrayList<>();
        for (Integer id :
                courses_) {
            for (Course c :
                    courseFileRepository.getAll()) {
                if (c.getId() == id)
                    tempCourses.add(c);
            }
            teacherFileRepository.update(tempTeacher, new Teacher(firstName, lastName, teacherId_, tempCourses));

        }
    }


    /**
     * Sort students descending by
     * ammount of enrolled courses
     *
     * @return: List<Student>
     */
    public List<Student> sortStudentsByEnrolledCourses() {
        return studentFileRepository.getAll().stream().sorted((x, y) -> (y.getEnrolledCourses().size() - x.getEnrolledCourses().size())).collect(Collectors.toList());
    }

    /**
     * Sort courses descending by
     * amount of credits
     *
     * @return: List<Course>
     */
    public List<Course> sortCourseByCredits() {
        return courseFileRepository.getAll().stream().sorted((x, y) -> (y.getCredits() - x.getCredits())).collect(Collectors.toList());
    }

    /**
     * Filter students by
     * max amount of enrolled Courses
     *
     * @param coursesCount: user given integer for filter
     * @return: List<Student>
     */
    public List<Student> filterStudentsByLessThenXCourses(Integer coursesCount) {
        return studentFileRepository.getAll().stream().filter(x -> x.getEnrolledCourses().size() <= coursesCount).collect(Collectors.toList());
    }

    /**
     * Filter courses by
     * max amount of enrollment
     *
     * @param enrollmentCount: user given integer for filter
     * @return: List<Course>
     */
    public List<Course> filterCourseByMaxEnrollment(Integer enrollmentCount) {
        return courseFileRepository.getAll().stream().filter(x -> x.getMaxEnrollment() <= enrollmentCount).collect(Collectors.toList());
    }

}
