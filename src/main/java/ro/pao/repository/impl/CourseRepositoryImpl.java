package ro.pao.repository.impl;

import ro.pao.config.DatabaseConfiguration;
import ro.pao.mapper.CourseMapper;
import ro.pao.model.Course;
import ro.pao.model.Student;
import ro.pao.repository.CourseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CourseRepositoryImpl implements CourseRepository {

    private static final CourseMapper courseMapper = CourseMapper.getInstance();

    @Override
    public Optional<Course> getObjectById(UUID id) {
        String selectSql = "SELECT * FROM course WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(1, id.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            return courseMapper.mapToCourse(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Course> getAll() {
        String selectSql = "SELECT * FROM course";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return courseMapper.mapToCourseList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public void addNewObject(Course course) {
        String insertSql = "INSERT INTO course (id, name, teacher_id, year) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, course.getId().toString());
            preparedStatement.setString(2, course.getName().toString());
            preparedStatement.setString(3, course.getTeacher_id().toString());
            preparedStatement.setInt(4, course.getYear().getValue());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteObjectById(UUID id) {
        String deleteSql = "DELETE FROM course WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setString(1, id.toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAllFromGivenList(List<Course> courseList) {
        courseList.forEach(this::addNewObject);
    }
}