package ac.kr.kw.judge.challenge.domain.event;

public class GradingResult {
    private GradingStatus status;
    private int score;

    public GradingResult() {
    }

    public GradingStatus getStatus() {
        return status;
    }

    public int getScore() {
        return score;
    }
}
