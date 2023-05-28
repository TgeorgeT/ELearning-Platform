package ro.pao.repository.impl;

import ro.pao.config.DatabaseConfiguration;
import ro.pao.mapper.EnrolledMapper;
import ro.pao.model.Enrolled;
import ro.pao.model.Student;
import ro.pao.repository.EnrolledRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EnrolledRepositoryImpl implements EnrolledRepository {

    private static final EnrolledMapper enrolledMapper = EnrolledMapper.getInstance();

    @Override
    public Optional<Enrolled> getObjectById(UUID id) {
        String selectSql = "SELECT * FROM enrolled WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(1, id.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            return enrolledMapper.mapToEnrolled(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Enrolled> getAll() {
        String selectSql = "SELECT * FROM enrolled";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return enrolledMapper.mapToEnrolledList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public void addNewObject(Enrolled enrolled) {
        String insertSql = "INSERT INTO enrolled (id, student_id, course_id) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, enrolled.getId().toString());
            preparedStatement.setString(2, enrolled.getStudent_id().toString());
            preparedStatement.setString(3, enrolled.getCourse_id().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteObjectById(UUID id) {
        String deleteSql = "DELETE FROM enrolled WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setString(1, id.toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAllFromGivenList(List<Enrolled> enrolledList) {
        enrolledList.forEach(this::addNewObject);
    }

}
