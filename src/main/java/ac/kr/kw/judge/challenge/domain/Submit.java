package ac.kr.kw.judge.challenge.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "submits")
public class Submit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long problemId;

    @JoinColumn(name = "participation_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Participation participation;

    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage programmingLanguage;

    @Column
    @CreationTimestamp
    private LocalDateTime submittedAt;

    @Column
    private ChallengeScore score;

    @Column //Lob or varchar
    private String sourceCode;

    @Column
    private SubmitStatus submitStatus;

    protected Submit() {
    }

    private Submit(Long problemId, Participation participation, ProgrammingLanguage programmingLanguage, String sourceCode) {
        this.problemId = problemId;
        this.participation = participation;
        this.programmingLanguage = programmingLanguage;
        this.score = ChallengeScore.ZERO;
        this.sourceCode = sourceCode;
        submitStatus = SubmitStatus.PENDING;
    }

    private Submit(Long id, Long problemId, Participation participation, ProgrammingLanguage programmingLanguage, String sourceCode) {
        this.id = id;
        this.problemId = problemId;
        this.participation = participation;
        this.programmingLanguage = programmingLanguage;
        this.score = ChallengeScore.ZERO;
        this.sourceCode = sourceCode;
        submitStatus = SubmitStatus.PENDING;
    }

    public static Submit withoutId(Long problemId, Participation participation, ProgrammingLanguage programmingLanguage, String sourceCode) {
        return new Submit(problemId, participation, programmingLanguage, sourceCode);
    }

    public static Submit withId(Long id, Long problemId, Participation participation, ProgrammingLanguage programmingLanguage, String sourceCode) {
        return new Submit(id, problemId, participation, programmingLanguage, sourceCode);
    }

    public void completeGrading(SubmitStatus status, ChallengeScore score) {
        this.submitStatus = status;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public Long getProblemId() {
        return problemId;
    }

    public Participation getParticipation() {
        return participation;
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public ChallengeScore getScore() {
        return score;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public SubmitStatus getSubmitStatus() {
        return submitStatus;
    }
}