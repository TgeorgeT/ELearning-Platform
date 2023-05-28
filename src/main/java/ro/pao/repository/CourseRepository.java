package ro.pao.repository;

import ro.pao.model.Course;
import ro.pao.model.ExampleClass;
import ro.pao.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository {

    Optional<Course> getObjectById(UUID id);

    void deleteObjectById(UUID id);


    void addNewObject(Course course);

    List<Course> getAll();

    void addAllFromGivenList(List<Course> courseList);
}