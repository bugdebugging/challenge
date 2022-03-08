package ac.kr.kw.judge.challenge.domain.event;

public class CompletedSubmit {
    private GradingResult result;
    private Long participationId;
    private Long submitId;

    public CompletedSubmit() {
    }

    public GradingResult getResult() {
        return result;
    }

    public Long getParticipationId() {
        return participationId;
    }

    public Long getSubmitId() {
        return submitId;
    }
}
