package ro.pao.model;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ro.pao.model.abstracts.User;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@SuperBuilder
public class Student extends User{
    String name;
    ReportCard reportCard;

}
