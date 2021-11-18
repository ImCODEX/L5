package Model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Teacher Class
 */

public class Teacher extends Person{
    private int teacherId;
    private List<Course> courses;

    /**
     * Teacher Constructor
     * @param firstName: String
     * @param lastName: String
     * @param courses_: List<Course>
     */
    public Teacher(String firstName, String lastName, int teacherId_, List<Course> courses_) {
        super(firstName, lastName);
        teacherId = teacherId_;
        courses = courses_;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * Equals method for comparison
     * Used especially in update() method from InMemoryRepository.java
     * @param o: another Teacher Object
     * @return: boolean
     */
    @Override
    public boolean equals(Object o){
        if (this == o ) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(getFirstName(), teacher.getFirstName()) && Objects.equals(getLastName(), teacher.getLastName());
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "firstName= " + getFirstName() +
                " lastName= " + getLastName() +
                '}';
    }

}
