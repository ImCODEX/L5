package Repo;

import Model.Student;

/**
 * StudentRepository Class used to
 * add, update, delete, return Student Objects
 */

public class StudentRepository extends InMemoryRepository<Student> {

    public StudentRepository() {
        super();
    }

}
