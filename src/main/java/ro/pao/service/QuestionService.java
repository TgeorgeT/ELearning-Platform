package ro.pao.service;

import ro.pao.model.Question;
import ro.pao.model.Teacher;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuestionService {
    Optional<Question> getById(UUID id);

    List<Question> getAllFromMap();


    void addOnlyOne(Question question);
    void addAllFromGivenList(List<Question> questions);

    void removeElementById(UUID id);

    void modifyElementById(UUID id, Question question);

    void addOption(UUID id, String option);

}
