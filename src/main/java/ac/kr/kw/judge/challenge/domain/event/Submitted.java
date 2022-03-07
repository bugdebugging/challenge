package ac.kr.kw.judge.challenge.domain.event;

public class Submitted {
    private Long problemId;
    private Long submitId;
    private Long participationId;
    private String programmingLanguage;
    private String sourceCode;

    private Submitted(Long problemId, Long submitId, Long participationId, String programmingLanguage, String sourceCode) {
        this.problemId = problemId;
        this.submitId = submitId;
        this.participationId = participationId;
        this.programmingLanguage = programmingLanguage;
        this.sourceCode = sourceCode;
    }

    public static Submitted of(Long problemId, Long submitId, Long participationId, String programmingLanguage, String sourceCode) {
        return new Submitted(problemId, submitId, participationId, programmingLanguage, sourceCode);
    }

    public Long getProblemId() {
        return problemId;
    }

    public Long getSubmitId() {
        return submitId;
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
