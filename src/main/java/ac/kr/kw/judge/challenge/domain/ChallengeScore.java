package ac.kr.kw.judge.challenge.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ChallengeScore {
    @Column(name = "score")
    private int score;

    public static final ChallengeScore ZERO = of(0);

    protected ChallengeScore() {
    }

    private ChallengeScore(int score) {
        this.score = score;
    }

    public static ChallengeScore of(int score) {
        return new ChallengeScore(score);
    }

    public int getScore() {
        return score;
    }

    public static ChallengeScore plusScore(ChallengeScore score1, ChallengeScore score2) {
        return ChallengeScore.of(score1.getScore() + score2.getScore());
    }

    public static ChallengeScore max(ChallengeScore score1, ChallengeScore score2) {
        if (score1.getScore() > score2.getScore())
            return ChallengeScore.of(score1.getScore());
        return ChallengeScore.of(score2.getScore());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChallengeScore that = (ChallengeScore) o;
        return score == that.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
