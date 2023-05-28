package ro.pao.repository;

import ro.pao.model.Enrolled;
import ro.pao.model.ExampleClass;
import ro.pao.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnrolledRepository {

    Optional<Enrolled> getObjectById(UUID id);

    void deleteObjectById(UUID id);


    void addNewObject(Enrolled enrolled);

    List<Enrolled> getAll();

    void addAllFromGivenList(List<Enrolled> studentList);
}