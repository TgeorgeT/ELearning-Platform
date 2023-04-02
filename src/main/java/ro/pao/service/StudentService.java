package ro.pao.service;

import ro.pao.model.Course;
import ro.pao.model.ExampleClass;
import ro.pao.model.Student;
import ro.pao.model.enums.CourseName;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * In interfata serviciului se definesc metodele pe care vrem sa le implementam.
 *
 * Metodele trebuie sa fie operatii pe baza tipului de obiect.
 *
 * In cazul de fata, avem clasa 'ExampleClass' si serviciul 'ExampleService'.
 * Vom avea operatii de baza doar pe obiectul 'ExampleClass'
 *
 * In cazul in care adaugam un nou obiect, de exemplu 'Animal', vom face un serviciu nou, pe nume 'AnimalService'
 * In acel serviciu se apeleaza doar metode pe obiectul 'Animal'.
 * De exemplu: "getAnimalById", "removeAnimalById" etc.
 */
public interface StudentService {

    Optional<Student> getById(UUID id);

    List<Student> getAllFromMap();


    void addOnlyOne(Student student);
    void addAllFromGivenList(List<Student> students);

    void removeElementById(UUID id);

    void modifyElementById(UUID id, Student student);

    List<Student> getAllByName(String name);
}
