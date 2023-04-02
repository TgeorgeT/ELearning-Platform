package ro.pao.service.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ro.pao.model.AnsweredQuiz;
import ro.pao.model.Quiz;
import ro.pao.service.AnsweredQuizService;
import ro.pao.service.ReportCardService;

import java.util.*;

@NoArgsConstructor
@Getter
public class AnsweredQuizServiceImpl implements AnsweredQuizService {
    static private Map<UUID, AnsweredQuiz> aQuizzes = new HashMap<>();

    @Override
    public Optional<AnsweredQuiz> getById(UUID id) {
        if (aQuizzes.containsKey(id)) {
            return Optional.of(aQuizzes.get(id));
        }
        return Optional.ofNullable(null);
    }

    @Override
    public List<AnsweredQuiz> getAllFromMap() {
        return aQuizzes.values().stream().toList();
    }

    @Override
    public void addOnlyOne(AnsweredQuiz aQuiz) {
        aQuizzes.put(aQuiz.getId(), aQuiz);
    }

    @Override
    public void addAllFromGivenList(List<AnsweredQuiz> aQuizzes) {
        aQuizzes.stream().map(c -> this.aQuizzes.put(c.getId(), c));
    }

    @Override
    public void removeElementById(UUID id) {
        aQuizzes.remove(id);
    }

    @Override
    public void modifyElementById(UUID id, AnsweredQuiz aQuizz) {
        removeElementById(id);
        addOnlyOne(aQuizz);
    }

    @Override
    public void grade(UUID id) {
        AnsweredQuiz aQuizz = aQuizzes.get(id);
        List<Integer> correctAnswers = aQuizz.getQuiz().getQuestionList().stream().map(question -> question.getAnswer()).toList();
        Double grade = 0.0;
        for (Integer i = 0; i < correctAnswers.size(); i++) {
            if (aQuizz.getAnswers().get(i) == correctAnswers.get(i)) {
                grade += aQuizz.getQuiz().getQuestionList().get(i).getPoints();
            }
        }

        ReportCardService reportCardService = new ReportCardServiceImpl();
        reportCardService.addGrade(aQuizz.getStudent().getReportCard().getId(),grade, aQuizz.getQuiz().getCourse());

    }
}
