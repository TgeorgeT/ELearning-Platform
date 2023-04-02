package ro.pao.model.abstracts;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.mindrot.jbcrypt.BCrypt;

import java.security.MessageDigest;
import java.security.SecureRandom;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@SuperBuilder
public abstract class User extends AbstractEntity {
    private String passwdHash;
    private String userName;

    public static String hashPasswd(String password){
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(password, salt);
    }

    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, passwdHash);
    }

}
