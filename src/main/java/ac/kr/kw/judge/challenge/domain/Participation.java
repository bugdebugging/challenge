package ac.kr.kw.judge.challenge.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "participations")
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private String name;

    @JoinColumn(name = "challenge_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Challenge challenge;

    @Embedded
    private ChallengeScore challengeScore;

    @OneToMany(mappedBy = "participation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Submit> submits = new ArrayList<>();

    protected Participation() {
    }

    private Participation(Long userId, String name, Challenge challenge) {
        this.userId = userId;
        this.name = name;
        this.challenge = challenge;
        this.challengeScore = ChallengeScore.ZERO;
    }

    private Participation(Long userId, String name, Challenge challenge, List<Submit> submits) {
        this.userId = userId;
        this.name = name;
        this.challenge = challenge;
        this.submits = submits;
        this.challengeScore = ChallengeScore.ZERO;
    }

    public static Participation of(Long userId, String name, Challenge challenge) {
        return new Participation(userId, name, challenge);
    }

    public static Participation withSubmits(Long userId, String name, Challenge challenge, List<Submit> submits) {
        return new Participation(userId, name, challenge, submits);
    }

    public void submitSolutionOfQuestion(Long problemId, ProgrammingLanguage programmingLanguage, String sourceCode) {
        this.submits.add(Submit.withoutId(problemId, this, programmingLanguage, sourceCode));
    }

    public void completeGradingOfSubmit(Long submitId, SubmitStatus status, ChallengeScore challengeScore) {
        Submit willGradedSubmit = this.submits.stream()
                .filter(submit -> submit.getId().equals(submitId))
                .findFirst().orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 submit이 존재하지 않습니다.");
                });
        willGradedSubmit.completeGrading(status, challengeScore);
        this.calculateTotalChallengeScore();
    }

    private void calculateTotalChallengeScore() {
        Map<Long, ChallengeScore> maximumScoreOfQuestions = new HashMap<>();
        this.submits.stream()
                .forEach(submit -> maximumScoreOfQuestions.put(submit.getProblemId(), ChallengeScore.ZERO));

        this.submits.stream()
                .forEach(submit -> {
                    ChallengeScore prevScore = maximumScoreOfQuestions.get(submit.getProblemId());
                    ChallengeScore largerScore = ChallengeScore.max(prevScore, submit.getScore());
                    maximumScoreOfQuestions.put(submit.getProblemId(), largerScore);
                });
        this.challengeScore = maximumScoreOfQuestions.values().stream()
                .reduce(ChallengeScore.ZERO, (score1, score2) -> ChallengeScore.plusScore(score1, score2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participation that = (Participation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public ChallengeScore getChallengeScore() {
        return challengeScore;
    }

    public List<Submit> getSubmits() {
        return submits;
    }
}
