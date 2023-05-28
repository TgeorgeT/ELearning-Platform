package ro.pao.mapper;

import ro.pao.model.Enrolled;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EnrolledMapper {

    private static final EnrolledMapper INSTANCE = new EnrolledMapper();

    private EnrolledMapper() {
    }

    public static EnrolledMapper getInstance() {
        return INSTANCE;
    }

    public Optional<Enrolled> mapToEnrolled(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return Optional.of(
                    Enrolled.builder()
                            .student_id(UUID.fromString(resultSet.getString("student_id")))
                            .course_id(UUID.fromString(resultSet.getString("course_id")))
                            .build()
            );
        } else {
            return Optional.empty();
        }
    }

    public List<Enrolled> mapToEnrolledList(ResultSet resultSet) throws SQLException {
        List<Enrolled> enrolledList = new ArrayList<>();
        while (resultSet.next()) {
            enrolledList.add(
                    Enrolled.builder()
                            .student_id(UUID.fromString(resultSet.getString("student_id")))
                            .course_id(UUID.fromString(resultSet.getString("course_id")))
                            .build()
            );
        }
        return enrolledList;
    }
}
