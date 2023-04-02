package ro.pao.service;

import ro.pao.model.Question;
import ro.pao.model.Quiz;
import ro.pao.model.Teacher;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuizService {
    Optional<Quiz> getById(UUID id);

    List<Quiz> getAllFromMap();


    void addOnlyOne(Quiz quiz);
    void addAllFromGivenList(List<Quiz> quizzes);

    void removeElementById(UUID id);

    void modifyElementById(UUID id, Quiz quiz);

}
