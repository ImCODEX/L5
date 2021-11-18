package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Course Class
 */

public class Course {
    private int id;
    private String name;
    private Teacher teacher;
    private int maxEnrollment;
    private List<Student> studentsEnrolled;
    private int credits;

    /**
     * Default Constructor
     */
    public Course() {
    }

    /**
     * Constructor without teacher and enrolledStudents
     * to resolve circular dependencies
     * @param id:               id
     * @param name:             String
     * @param maxEnrollment:    int - maximum number of Student's
     * @param credits:          int
     */
    public Course(int id, String name, int maxEnrollment, int credits) {
        this.id = id;
        this.name = name;
        this.maxEnrollment = maxEnrollment;
        this.credits = credits;
    }

    /**
     * Course Class
     *
     * @param id:               id
     * @param name:             String
     * @param teacher:          Teacher Object
     * @param maxEnrollment:    int - maximum number of Student's
     * @param studentsEnrolled: List<Student>
     * @param credits:          int
     */

    public Course(int id, String name, Teacher teacher, int maxEnrollment, List<Student> studentsEnrolled, int credits) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.maxEnrollment = maxEnrollment;
        this.studentsEnrolled = studentsEnrolled;
        this.credits = credits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public List<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(List<Student> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }

    public void enrollStudent(Student student){ this.studentsEnrolled.add(student); }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * Equals method for comparison
     * Used especially in update() method from InMemoryRepository.java
     *
     * @param o: another Teacher Object
     * @return: boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(teacher, course.getTeacher()) && Objects.equals(name, course.getName());
    }

    /**
     * toString() method for Course
     * checks for null teacher
     * @return: String
     */
    @Override
    public String toString() {
        List<Integer> studentsId = new ArrayList<>();
        for (Student s:
             studentsEnrolled) {
            studentsId.add((int) s.getStudentId());
        }
        if (teacher != null)
            return "Course{" +
                    "id=" + id +
                    ", name=" + name + '\'' +
                    ", teacher=" + teacher +
                    ", maxEnrollment=" + maxEnrollment +
                    ", studentsEnrolled=" + studentsId +
                    ", credits=" + credits +
                    '}';
        else
            return "Course{" +
                    "id=" + id +
                    ", name=" + name + '\'' +
                    ", teacher=null" +
                    ", maxEnrollment=" + maxEnrollment +
                    ", studentsEnrolled=" + studentsId +
                    ", credits=" + credits +
                    '}';
    }


}
