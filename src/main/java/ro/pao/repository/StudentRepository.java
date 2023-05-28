package ro.pao.repository;

import ro.pao.model.ExampleClass;
import ro.pao.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository {

    Optional<Student> getObjectById(UUID id);

    void deleteObjectById(UUID id);


    void addNewObject(Student student);

    List<Student> getAll();

    void addAllFromGivenList(List<Student> studentList);
}