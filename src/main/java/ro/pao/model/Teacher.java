package ro.pao.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.mindrot.jbcrypt.BCrypt;
import ro.pao.model.abstracts.AbstractEntity;
import ro.pao.model.abstracts.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@SuperBuilder

public class Teacher extends User {
    Name name;

    public String[] toStringArray(){
        String[] array = {this.getId().toString(), this.getName().lastName(), this.getName().firstName()};
        return array;
    }
}
