package ac.kr.kw.judge.challenge.domain.event;

public class ParticipantResult {
    private String username;
    private int challengeScore;

    private ParticipantResult(String username, int challengeScore) {
        this.username = username;
        this.challengeScore = challengeScore;
    }

    public static ParticipantResult of(String username, int challengeScore) {
        return new ParticipantResult(username, challengeScore);
    }

    public String getUsername() {
        return username;
    }

    public int getChallengeScore() {
        return challengeScore;
    }
}
