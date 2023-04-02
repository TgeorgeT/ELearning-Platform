package ro.pao.service;

import ro.pao.model.AnsweredQuiz;
import ro.pao.model.Course;
import ro.pao.model.ReportCard;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReportCardService {
    Optional<ReportCard> getById(UUID id);

    List<ReportCard> getAllFromMap();


    void addOnlyOne(ReportCard reportCard);
    void addAllFromGivenList(List<ReportCard> reportCards);

    void removeElementById(UUID id);

    void modifyElementById(UUID id, ReportCard reportCard);

    void addGrade(UUID id, Double grade, Course course);
}
