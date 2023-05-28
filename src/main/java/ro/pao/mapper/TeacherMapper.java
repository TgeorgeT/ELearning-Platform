package ro.pao.mapper;


import ro.pao.model.ExampleClass;
import ro.pao.model.Name;
import ro.pao.model.Student;
import ro.pao.model.Teacher;
import ro.pao.model.enums.EnumExample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TeacherMapper {

    private static final TeacherMapper INSTANCE = new TeacherMapper();

    private TeacherMapper() {
    }

    public static TeacherMapper getInstance() {
        return INSTANCE;
    }


    public Optional<Teacher> mapToTeacher(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return Optional.of(
                    Teacher.builder()
                            .id(UUID.fromString(resultSet.getString("id")))
                            .name(new Name(resultSet.getString("last_name"), resultSet.getString("first_name")))
                            .userName(resultSet.getString("user_name"))
                            .passwdHash(resultSet.getString("password_hash"))
                            .build()
            );
        } else {
            return Optional.empty();
        }
    }

    public List<Teacher> mapToTeacherList(ResultSet resultSet) throws SQLException {
        List<Teacher> teacherList = new ArrayList<>();
        while (resultSet.next()) {
            teacherList.add(
                    Teacher.builder()
                            .id(UUID.fromString(resultSet.getString("id")))
                            .name(new Name(resultSet.getString("last_name"), resultSet.getString("first_name")))
                            .userName(resultSet.getString("user_name"))
                            .passwdHash(resultSet.getString("password_hash"))
                            .build()
            );
        }

        return teacherList;
    }
}