package ro.pao.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
//import ro.pao.application.csv.CsvReader;
//import ro.pao.application.csv.CsvWriter;
import ro.pao.model.Enrolled;
import ro.pao.model.Student;
import ro.pao.model.Teacher;
import ro.pao.repository.StudentRepository;
import ro.pao.repository.TeacherRepository;
import ro.pao.service.StudentService;
import ro.pao.service.TeacherService;

import java.util.*;

/**
 * Aici implementam metodele din interfata serviciului definit.
 */
@RequiredArgsConstructor
@Getter
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Optional<Student> getById(UUID id) {
        return studentRepository.getObjectById(id);
    }


    @Override
    public List<Student> getAll() {
        return studentRepository.getAll();
    }

    @Override
    public void addAllFromGivenList(List<Student> studentList) {
        studentRepository.addAllFromGivenList(studentList);
    }

    @Override
    public void addOnlyOne(Student student) {
        studentRepository.addNewObject(student);
    }

    @Override
    public void removeElementById(UUID id) {
        studentRepository.deleteObjectById(id);
    }

}