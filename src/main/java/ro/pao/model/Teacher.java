package ro.pao.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.mindrot.jbcrypt.BCrypt;
import ro.pao.model.abstracts.AbstractEntity;
import ro.pao.model.abstracts.User;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@SuperBuilder

public class Teacher extends User {
    Name name;
}
