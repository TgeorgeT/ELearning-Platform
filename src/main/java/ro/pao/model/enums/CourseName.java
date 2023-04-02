package ro.pao.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum CourseName {
    DSA("Data Structures and Algorithms"),
    OOP("Object Oriented Programming"),
    AI("Artificial Intelligence"),
    PS("Probability and Statistics"),
    NONE("none");
    private String name;

    public static CourseName getEnumByType(final String name) {
        return Arrays.stream(CourseName.values())
                .filter(eggColor -> eggColor.getName().equals(name))
                .findFirst()
                .orElse(NONE);
    }
}
