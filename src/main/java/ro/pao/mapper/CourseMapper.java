package ro.pao.mapper;

import ro.pao.model.Course;
import ro.pao.model.enums.CourseName;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.Year;

public class CourseMapper {

    private static final CourseMapper INSTANCE = new CourseMapper();

    private CourseMapper() {
    }

    public static CourseMapper getInstance() {
        return INSTANCE;
    }

    public Optional<Course> mapToCourse(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return Optional.of(
                    Course.builder()
                            .id(UUID.fromString(resultSet.getString("id")))
                            .name(CourseName.valueOf(resultSet.getString("name")))
                            .teacher_id(UUID.fromString(resultSet.getString("teacher_id")))
                            .year(Year.parse(resultSet.getString("year")))
                            .build()
            );
        } else {
            return Optional.empty();
        }
    }

    public List<Course> mapToCourseList(ResultSet resultSet) throws SQLException {
        List<Course> courseList = new ArrayList<>();
        while (resultSet.next()) {
            courseList.add(
                    Course.builder()
                            .id(UUID.fromString(resultSet.getString("id")))
                            .name(CourseName.valueOf(resultSet.getString("name")))
                            .teacher_id(UUID.fromString(resultSet.getString("teacher_id")))
                            .year(Year.parse(resultSet.getString("year")))
                            .build()
            );
        }
        return courseList;
    }
}
