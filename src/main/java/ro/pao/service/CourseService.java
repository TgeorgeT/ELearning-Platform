package ro.pao.service;

import ro.pao.model.Course;
import ro.pao.model.ExampleClass;
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
public interface CourseService {

     Optional<Course> getById(UUID id);

    List<Course> getAll();

    void addOnlyOne(Course course);
    void addAllFromGivenList(List<Course> courses);

    void removeElementById(UUID id);


}
