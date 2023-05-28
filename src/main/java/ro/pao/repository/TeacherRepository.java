package ro.pao.repository;

import ro.pao.model.ExampleClass;
import ro.pao.model.Teacher;

import java.util.List;
import java.util.Optional;
import java.util.Optional;
import java.util.UUID;

public interface TeacherRepository {

    Optional<Teacher> getObjectById(UUID id);

    void deleteObjectById(UUID id);

    void updateObjectById(UUID id, Teacher newObject);

    void addNewObject(Teacher teacher);

    List<Teacher> getAll();

    void addAllFromGivenList(List<Teacher> teacherList);
}