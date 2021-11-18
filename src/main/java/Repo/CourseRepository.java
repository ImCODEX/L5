package Repo;

import Model.Course;

/**
 * CourseRepository Class used to
 * add, update, delete, return Course Objects
 */

public class CourseRepository extends InMemoryRepository<Course>{

    public CourseRepository() {
        super();
    }
}
