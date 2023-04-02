package ro.pao.service.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ro.pao.model.Course;
import ro.pao.model.Student;
import ro.pao.model.enums.CourseName;
import ro.pao.service.CourseService;
import ro.pao.service.StudentService;

import java.util.*;

@NoArgsConstructor
@Getter
public class StudentServiceImpl implements StudentService {
    static private Map<UUID, Student> students = new HashMap<>();

    @Override
    public Optional<Student> getById(UUID id) {
        if (students.containsKey(id)) {
            return Optional.of(students.get(id));
        }
        return Optional.ofNullable(null);
    }

    @Override
    public List<Student> getAllFromMap() {
        return students.values().stream().toList();
    }

    @Override
    public void addOnlyOne(Student student) {
        students.put(student.getId(), student);
    }

    @Override
    public void addAllFromGivenList(List<Student> students) {
        students.stream().map(c -> this.students.put(c.getId(), c));
    }

    @Override
    public void removeElementById(UUID id) {
        students.remove(id);
    }

    @Override
    public void modifyElementById(UUID id, Student student) {
        removeElementById(id);
        addOnlyOne(student);
    }

    @Override
    public List<Student> getAllByName(String name) {
        return students.values().stream().filter(c -> c.getName().equals(name)).toList();
    }
}
