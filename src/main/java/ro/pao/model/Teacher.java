package ro.pao.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.mindrot.jbcrypt.BCrypt;
import ro.pao.model.abstracts.AbstractEntity;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@SuperBuilder

public class Teacher extends AbstractEntity {
    String name;
}
