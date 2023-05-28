package ro.pao.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
//import ro.pao.application.csv.CsvReader;
//import ro.pao.application.csv.CsvWriter;
import ro.pao.model.Course;
import ro.pao.model.Student;
import ro.pao.model.Teacher;
import ro.pao.repository.CourseRepository;
import ro.pao.repository.StudentRepository;
import ro.pao.repository.TeacherRepository;
import ro.pao.service.CourseService;
import ro.pao.service.StudentService;
import ro.pao.service.TeacherService;

import java.util.*;

/**
 * Aici implementam metodele din interfata serviciului definit.
 */
@RequiredArgsConstructor
@Getter
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Optional<Course> getById(UUID id) {
        return courseRepository.getObjectById(id);
    }


    @Override
    public List<Course > getAll() {
        return courseRepository.getAll();
    }

    @Override
    public void addAllFromGivenList(List<Course> courseList) {
        courseRepository.addAllFromGivenList(courseList);
    }

    @Override
    public void addOnlyOne(Course course) {
        courseRepository.addNewObject(course);
    }

    @Override
    public void removeElementById(UUID id) {
        courseRepository.deleteObjectById(id);
    }

}