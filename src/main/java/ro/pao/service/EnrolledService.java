package ro.pao.service;

import ro.pao.model.Course;
import ro.pao.model.Enrolled;
import ro.pao.model.ExampleClass;
import ro.pao.model.enums.CourseName;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface EnrolledService {

    Optional<Enrolled> getById(UUID id);

    List<Enrolled> getAll();

    void addOnlyOne(Enrolled enrolled);
    void addAllFromGivenList(List<Enrolled> enrolled);

    void removeElementById(UUID id);


}
