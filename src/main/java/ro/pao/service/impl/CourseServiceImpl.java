package ro.pao.service.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ro.pao.model.Course;
import ro.pao.model.enums.CourseName;
import ro.pao.service.CourseService;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class CourseServiceImpl implements CourseService {
    static private Map<UUID, Course> courses = new HashMap<>();

    @Override
    public Optional<Course> getById(UUID id) {
        if (courses.containsKey(id)) {
            return Optional.of(courses.get(id));
        }
        return Optional.ofNullable(null);
    }

    @Override
    public List<Course> getAllFromMap() {
        return courses.values().stream().collect(Collectors.toList());
    }

    @Override
    public void addOnlyOne(Course course) {
        courses.put(course.getId(), course);
    }

    @Override
    public void addAllFromGivenList(List<Course> courses) {
        courses.stream().forEach(c -> this.courses.put(c.getId(), c));
    }
    @Override
    public void removeElementById(UUID id){
        courses.remove(id);
    }

    @Override
    public void modifyElementById(UUID id, Course newCourse) {
        removeElementById(id);
        addOnlyOne(newCourse);
    }

    @Override
    public List<Course> getAllByName(CourseName name) {
        return courses.values().stream().filter(c -> c.getName().equals(name)).collect(Collectors.toList());
    }
}
