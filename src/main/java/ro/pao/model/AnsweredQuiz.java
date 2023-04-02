package ro.pao.model;


import lombok.*;
import lombok.experimental.SuperBuilder;
import ro.pao.model.abstracts.AbstractEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@SuperBuilder
public class AnsweredQuiz extends AbstractEntity {
    Quiz quiz;
    Student student;
    Double grade;
    List<Integer> answers = new ArrayList<>();
}
