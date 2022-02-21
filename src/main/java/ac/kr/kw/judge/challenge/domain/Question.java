package ac.kr.kw.judge.challenge.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long problemId;

    @Column
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Challenge challenge;

    protected Question() {
    }

    private Question(Long problemId, String title) {
        this.problemId = problemId;
        this.title = title;
    }

    public static Question of(Long problemId, String title) {
        return new Question(problemId, title);
    }

    public Long getId() {
        return id;
    }

    public Long getProblemId() {
        return problemId;
    }

    public String getTitle() {
        return title;
    }


    public Challenge getChallenge() {
        return challenge;
    }

    //for mapping
    public void setChallenge(Challenge challenge){
        this.challenge=challenge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
