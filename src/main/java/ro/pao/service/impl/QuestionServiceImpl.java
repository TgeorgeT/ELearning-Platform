package ro.pao.service.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ro.pao.model.Course;
import ro.pao.model.Question;
import ro.pao.model.Student;
import ro.pao.model.Teacher;
import ro.pao.model.enums.CourseName;
import ro.pao.service.CourseService;
import ro.pao.service.QuestionService;
import ro.pao.service.StudentService;
import ro.pao.service.TeacherService;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class QuestionServiceImpl implements QuestionService {
    static private Map<UUID, Question> questions = new HashMap<>();

    @Override
    public Optional<Question> getById(UUID id) {
        if (questions.containsKey(id)) {
            return Optional.of(questions.get(id));
        }
        return Optional.ofNullable(null);
    }

    @Override
    public List<Question> getAllFromMap() {
        return questions.values().stream().collect(Collectors.toList());
    }

    @Override
    public void addOnlyOne(Question question) {
        questions.put(question.getId(), question);
    }

    @Override
    public void addAllFromGivenList(List<Question> questions) {
        questions.stream().forEach(c -> this.questions.put(c.getId(), c));
    }

    @Override
    public void removeElementById(UUID id) {
        questions.remove(id);
    }

    @Override
    public void modifyElementById(UUID id, Question question) {
        removeElementById(id);
        addOnlyOne(question);
    }

    @Override
    public void addOption(UUID id, String option) {
        questions.get(id).getOptions().add(option);
    }
}
