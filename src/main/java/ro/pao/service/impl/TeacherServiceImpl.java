package ro.pao.service.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ro.pao.model.Course;
import ro.pao.model.Student;
import ro.pao.model.Teacher;
import ro.pao.model.enums.CourseName;
import ro.pao.service.CourseService;
import ro.pao.service.StudentService;
import ro.pao.service.TeacherService;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class TeacherServiceImpl implements TeacherService {
    static private Map<UUID, Teacher> teachers = new HashMap<>();

    @Override
    public Optional<Teacher> getById(UUID id) {
        if (teachers.containsKey(id)) {
            return Optional.of(teachers.get(id));
        }
        return Optional.ofNullable(null);
    }

    @Override
    public List<Teacher> getAllFromMap() {
        return teachers.values().stream().collect(Collectors.toList());
    }

    @Override
    public void addOnlyOne(Teacher teacher) {
        teachers.put(teacher.getId(), teacher);
    }

    @Override
    public void addAllFromGivenList(List<Teacher> teachers) {
        teachers.stream().forEach(c -> this.teachers.put(c.getId(), c));
    }

    @Override
    public void removeElementById(UUID id) {
        teachers.remove(id);
    }

    @Override
    public void modifyElementById(UUID id, Teacher teacher) {
        removeElementById(id);
        addOnlyOne(teacher);
    }

    @Override
    public List<Teacher> getAllByName(String name) {
        return teachers.values().stream().filter(c -> c.getName().equals(name)).collect(Collectors.toList());
    }
}
