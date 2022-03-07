package ac.kr.kw.judge.challenge.domain.event;

public class Submitted {
    private Long problemId;
    private Long participationId;
    private String programmingLanguage;
    private String sourceCode;

    private Submitted(Long problemId, Long participationId, String programmingLanguage, String sourceCode) {
        this.problemId = problemId;
        this.participationId = participationId;
        this.programmingLanguage = programmingLanguage;
        this.sourceCode = sourceCode;
    }

    public static Submitted of(Long problemId, Long participationId, String programmingLanguage, String sourceCode) {
        return new Submitted(problemId, participationId, programmingLanguage, sourceCode);
    }

    public Long getProblemId() {
        return problemId;
    }

    public Long getParticipationId() {
        return participationId;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public String getSourceCode() {
        return sourceCode;
    }
}
