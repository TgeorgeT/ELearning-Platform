package ro.pao.service.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ro.pao.model.AnsweredQuiz;
import ro.pao.model.Course;
import ro.pao.model.ReportCard;
import ro.pao.service.ReportCardService;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class ReportCardServiceImpl implements ReportCardService {
    static private Map<UUID, ReportCard> reportCards = new HashMap<>();

    @Override
    public Optional<ReportCard> getById(UUID id) {
        if (reportCards.containsKey(id)) {
            return Optional.of(reportCards.get(id));
        }
        return Optional.ofNullable(null);
    }

    @Override
    public List<ReportCard> getAllFromMap() {
        return reportCards.values().stream().collect(Collectors.toList());
    }

    @Override
    public void addOnlyOne(ReportCard reportCard) {
        reportCards.put(reportCard.getId(), reportCard);
    }

    @Override
    public void addAllFromGivenList(List<ReportCard> reportCards) {
        reportCards.stream().forEach(c -> this.reportCards.put(c.getId(), c));
    }

    @Override
    public void removeElementById(UUID id) {
        reportCards.remove(id);
    }

    @Override
    public void modifyElementById(UUID id, ReportCard reportCard) {
        removeElementById(id);
        addOnlyOne(reportCard);
    }

    @Override
    public void addGrade(UUID id, Double grade, Course course) {
        Map<UUID, List<Double>> grades = reportCards.get(id).getGrades();
        if(grades.containsKey(course.getId())) {
            grades.get(course.getId()).add(grade);
        }
        else {
            grades.put(course.getId(), new ArrayList<>());
            grades.get(course.getId()).add(grade);
        }
    }
}
