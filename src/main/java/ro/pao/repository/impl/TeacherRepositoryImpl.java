package ro.pao.repository.impl;

import ro.pao.config.DatabaseConfiguration;
import ro.pao.mapper.TeacherMapper;
import ro.pao.model.Teacher;
import ro.pao.repository.TeacherRepository;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ro.pao.application.utils.Constants.CSV_PATH_WRITE;

public class TeacherRepositoryImpl implements TeacherRepository {



    private static final TeacherMapper teacherMapper = TeacherMapper.getInstance();
    private static final Logger logger = Logger.getGlobal();




    @Override
    public Optional<Teacher> getObjectById(UUID id) {
        String selectSql = "SELECT * FROM teacher WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(1, id.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            return teacherMapper.mapToTeacher(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void deleteObjectById(UUID id) {
        String updateNameSql = "DELETE FROM teacher WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql)) {
            preparedStatement.setString(1, id.toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateObjectById(UUID id, Teacher newObject) {
        String updateNameSql = "UPDATE teacher SET name=? WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql)) {
            preparedStatement.setString(1, newObject.getName().toString());
            preparedStatement.setString(2, id.toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNewObject(Teacher teacher) {
        String insertSql = "INSERT INTO teacher (id, first_name, last_name, user_name, password_hash) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, teacher.getId().toString());
            preparedStatement.setString(2, teacher.getName().firstName());
            preparedStatement.setString(3, teacher.getName().lastName());
            preparedStatement.setString(4, teacher.getUserName());
            preparedStatement.setString(5, teacher.getPasswdHash());

            preparedStatement.executeUpdate();
            logger.log(Level.INFO, "added teacher");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "error when adding teacher");
        }
    }

    @Override
    public List<Teacher> getAll() {
        String selectSql = "SELECT * FROM teacher";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            return teacherMapper.mapToTeacherList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    @Override
    public void addAllFromGivenList(List<Teacher> teacherList) {
        teacherList.forEach(this::addNewObject);
    }
}