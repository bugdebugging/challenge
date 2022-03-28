package ac.kr.kw.judge.challenge.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Author {
    @Column(name = "author")
    private String name;

    protected Author() {
    }

    private Author(String name) {
        this.name = name;
    }

    public static Author of(String name) {
        return new Author(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
