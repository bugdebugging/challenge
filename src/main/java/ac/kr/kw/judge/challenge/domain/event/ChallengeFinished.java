package ac.kr.kw.judge.challenge.domain.event;

import java.util.List;

public class ChallengeFinished {
    private Long challengeId;
    private List<ParticipantResult> participantResults;

    private ChallengeFinished(Long challengeId, List<ParticipantResult> participantResults) {
        this.challengeId = challengeId;
        this.participantResults = participantResults;
    }

    public static ChallengeFinished of(Long challengeId, List<ParticipantResult> participantResults) {
        return new ChallengeFinished(challengeId, participantResults);
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public List<ParticipantResult> getParticipantResults() {
        return participantResults;
    }
}
