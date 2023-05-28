package ro.pao.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ro.pao.model.abstracts.AbstractEntity;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@SuperBuilder
public class Enrolled extends AbstractEntity {
    UUID student_id;
    UUID course_id;
}
