package ro.pao.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ro.pao.model.abstracts.AbstractEntity;
import ro.pao.model.abstracts.User;
import ro.pao.model.enums.CourseName;

import java.time.Year;
import java.util.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@SuperBuilder
public class Course extends AbstractEntity {
    private CourseName name;
    private Set<UUID> students = new HashSet<>();
    private Teacher teacher;
    private Year year;
    private List<Quiz>quizes = new ArrayList<>();
    

}
