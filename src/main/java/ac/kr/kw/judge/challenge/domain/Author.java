package ac.kr.kw.judge.challenge.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Author {
    @Column
    private Long userId;
    @Column
    private String name;
    @Column
    private int accumulateScore;

    protected Author() {
    }

    private Author(Long userId, String name, int accumulateScore) {
        this.userId = userId;
        this.name = name;
        this.accumulateScore = accumulateScore;
    }

    public static Author of(Long userId, String name, int accumulateScore) {
        return new Author(userId, name, accumulateScore);
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getAccumulateScore() {
        return accumulateScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return accumulateScore == author.accumulateScore && Objects.equals(userId, author.userId) && Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, accumulateScore);
    }
}
