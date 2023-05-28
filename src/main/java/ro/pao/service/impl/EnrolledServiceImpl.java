package ro.pao.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
//import ro.pao.application.csv.CsvReader;
//import ro.pao.application.csv.CsvWriter;
import ro.pao.model.Enrolled;
import ro.pao.model.Teacher;
import ro.pao.repository.EnrolledRepository;
import ro.pao.repository.TeacherRepository;
import ro.pao.service.EnrolledService;
import ro.pao.service.TeacherService;

import java.util.*;

/**
 * Aici implementam metodele din interfata serviciului definit.
 */
@RequiredArgsConstructor
@Getter
public class EnrolledServiceImpl implements EnrolledService {

    private final EnrolledRepository enrolledRepository;

    @Override
    public Optional<Enrolled> getById(UUID id) {
        return enrolledRepository.getObjectById(id);
    }


    @Override
    public List<Enrolled> getAll() {
        return enrolledRepository.getAll();
    }


    @Override
    public void addAllFromGivenList(List<Enrolled> enrolledList) {
        enrolledRepository.addAllFromGivenList(enrolledList);
    }

    @Override
    public void addOnlyOne(Enrolled enrolled) {
        enrolledRepository.addNewObject(enrolled);
    }

    @Override
    public void removeElementById(UUID id) {
        enrolledRepository.deleteObjectById(id);
    }
}