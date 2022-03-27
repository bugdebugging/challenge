package ac.kr.kw.judge.challenge.domain.event;

public class CompletedSubmit {
    private GradingResult result;
    private String username;
    private Long challengeId;
    private Long submitId;

    public CompletedSubmit() {
    }

    public GradingResult getResult() {
        return result;
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
}
