package ro.pao.repository.impl;

import ro.pao.config.DatabaseConfiguration;
import ro.pao.mapper.ExampleClassMapper;
import ro.pao.mapper.StudentMapper;
import ro.pao.model.ExampleClass;
import ro.pao.model.Student;
import ro.pao.repository.StudentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StudentRepositoryImpl implements StudentRepository {

    private static final StudentMapper studentMapper = StudentMapper.getInstance();


    @Override
    public Optional<Student> getObjectById(UUID id) {
        String selectSql = "SELECT * FROM student WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(1, id.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            return studentMapper.mapToStudent(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void deleteObjectById(UUID id) {
        String updateNameSql = "DELETE FROM student WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql)) {
            preparedStatement.setString(1, id.toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void addNewObject(Student student) {
        String insertSql = "INSERT INTO student (id, first_name, last_name, user_name, password_hash) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, student.getId().toString());
            preparedStatement.setString(2, student.getName().firstName());
            preparedStatement.setString(3, student.getName().lastName());
            preparedStatement.setString(4, student.getUserName());
            preparedStatement.setString(5, student.getPasswdHash());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> getAll() {
        String selectSql = "SELECT * FROM student";

        try (Connection connection = DatabaseConfiguration.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            return studentMapper.mapToStudentList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    @Override
    public void addAllFromGivenList(List<Student> studentList) {
        studentList.forEach(this::addNewObject);
    }
}