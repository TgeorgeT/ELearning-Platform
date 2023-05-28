package ro.pao.service;

import ro.pao.model.ExampleClass;
import ro.pao.model.Teacher;

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
public interface TeacherService {

    Optional<Teacher> getById(UUID id);


    List<Teacher> getAll();

    List<Teacher> getAllWithCondition();

    void addAllFromGivenList(List<Teacher> teacherList);

    void addOnlyOne(Teacher teacher);

    void removeElementById(UUID id);

    void modificaElementById(UUID id, Teacher teacher);
}