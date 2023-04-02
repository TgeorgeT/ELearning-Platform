package ro.pao.model;

public record Name(String lastName, String firstName)  implements Comparable<Name>{
    @Override
    public int compareTo(Name name) {
        return (this.lastName+this.firstName).compareTo(name.lastName+name.firstName);
    }
}
