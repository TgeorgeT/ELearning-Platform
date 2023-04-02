package ro.pao.service;

import ro.pao.model.AnsweredQuiz;
import ro.pao.model.Quiz;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnsweredQuizService {
    Optional<AnsweredQuiz> getById(UUID id);

    List<AnsweredQuiz> getAllFromMap();


    void addOnlyOne(AnsweredQuiz aQuiz);
    void addAllFromGivenList(List<AnsweredQuiz> aQuizzes);

    void removeElementById(UUID id);

    void modifyElementById(UUID id, AnsweredQuiz aQuiz);

    void grade(UUID id);

}
