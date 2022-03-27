package ac.kr.kw.judge.challenge.domain.event;

public class Submitted {
    private Long problemId;
    private String username;
    private Long challengeId;
    private Long submitId;
    private String programmingLanguage;
    private String sourceCode;

    private Submitted(Long problemId, String username, Long challengeId, Long submitId, String programmingLanguage, String sourceCode) {
        this.problemId = problemId;
        this.username = username;
        this.challengeId = challengeId;
        this.submitId = submitId;
        this.programmingLanguage = programmingLanguage;
        this.sourceCode = sourceCode;
    }

    public static Submitted of(Long problemId, String username, Long challengeId, Long submitId, String programmingLanguage, String sourceCode) {
        return new Submitted(problemId, username, challengeId, submitId, programmingLanguage, sourceCode);
    }

    public Long getProblemId() {
        return problemId;
    }

    public String getUsername() {
        return username;
    }

    public Long getChallengeId() {
        return challengeId;
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
