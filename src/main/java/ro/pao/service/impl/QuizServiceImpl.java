package ro.pao.service.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ro.pao.model.Course;
import ro.pao.model.Quiz;
import ro.pao.model.Student;
import ro.pao.model.enums.CourseName;
import ro.pao.service.CourseService;
import ro.pao.service.QuizService;
import ro.pao.service.StudentService;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class QuizServiceImpl implements QuizService {
    static private Map<UUID, Quiz> quizzes = new HashMap<>();

    @Override
    public Optional<Quiz> getById(UUID id) {
        if (quizzes.containsKey(id)) {
            return Optional.of(quizzes.get(id));
        }
        return Optional.ofNullable(null);
    }

    @Override
    public List<Quiz> getAllFromMap() {
        return quizzes.values().stream().collect(Collectors.toList());
    }

    @Override
    public void addOnlyOne(Quiz quiz) {
        quizzes.put(quiz.getId(), quiz);
    }

    @Override
    public void addAllFromGivenList(List<Quiz> quizzes) {
        quizzes.stream().forEach(c -> this.quizzes.put(c.getId(), c));
    }

    @Override
    public void removeElementById(UUID id) {
        quizzes.remove(id);
    }

    @Override
    public void modifyElementById(UUID id, Quiz quiz) {
        removeElementById(id);
        addOnlyOne(quiz);
    }

}
