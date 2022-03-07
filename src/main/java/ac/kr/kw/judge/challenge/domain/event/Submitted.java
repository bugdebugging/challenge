package ac.kr.kw.judge.challenge.domain.event;

public class Submitted {
    private Long problemId;
    private Long participationId;
    private Long submitId;
    private String programmingLanguage;
    private String sourceCode;

    private Submitted(Long problemId, Long participationId, Long submitId, String programmingLanguage, String sourceCode) {
        this.problemId = problemId;
        this.participationId = participationId;
        this.submitId = submitId;
        this.programmingLanguage = programmingLanguage;
        this.sourceCode = sourceCode;
    }

    public static Submitted of(Long problemId, Long participationId, Long submitId, String programmingLanguage, String sourceCode) {
        return new Submitted(problemId, participationId, submitId, programmingLanguage, sourceCode);
    }

    public Long getProblemId() {
        return problemId;
    }

    public Long getParticipationId() {
        return participationId;
    }

    public Long getSubmitId() {
        return submitId;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public String getSourceCode() {
        return sourceCode;
    }
}
