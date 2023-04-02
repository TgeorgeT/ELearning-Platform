package ro.pao.service;

import ro.pao.model.*;
import ro.pao.model.enums.CourseName;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeacherService {

    Optional<Teacher> getById(UUID id);

    List<Teacher> getAllFromMap();


    void addOnlyOne(Teacher teacher);
    void addAllFromGivenList(List<Teacher> teachers);

    void removeElementById(UUID id);

    void modifyElementById(UUID id, Teacher teacher);

    List<Teacher> getAllByName(Name name);
}
