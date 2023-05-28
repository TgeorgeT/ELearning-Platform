package ro.pao.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
//import ro.pao.application.csv.CsvReader;
//import ro.pao.application.csv.CsvWriter;
import ro.pao.model.Teacher;
import ro.pao.repository.TeacherRepository;
import ro.pao.service.TeacherService;

import java.util.*;

/**
 * Aici implementam metodele din interfata serviciului definit.
 */
@RequiredArgsConstructor
@Getter
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public Optional<Teacher> getById(UUID id) {
        return teacherRepository.getObjectById(id);
    }


    @Override
    public List<Teacher> getAll() {
        return teacherRepository.getAll();
    }

    @Override
    public List<Teacher> getAllWithCondition() {
        return null;
    }

    @Override
    public void addAllFromGivenList(List<Teacher> teacherList) {
        teacherRepository.addAllFromGivenList(teacherList);
    }

    @Override
    public void addOnlyOne(Teacher teacher) {
        teacherRepository.addNewObject(teacher);
    }

    @Override
    public void removeElementById(UUID id) {
        teacherRepository.deleteObjectById(id);
    }

    @Override
    public void modificaElementById(UUID id, Teacher teacher) {
        teacherRepository.updateObjectById(id, teacher);
    }
}