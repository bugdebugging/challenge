package ac.kr.kw.judge.challenge.dto.out;

import ac.kr.kw.judge.challenge.domain.Participation;

public class ParticipationDto {
    private Long participationId;
    private String name;
    private int challengeScore;

    private ParticipationDto(Long participationId, String name, int challengeScore) {
        this.participationId = participationId;
        this.name = name;
        this.challengeScore = challengeScore;
    }

    public static ParticipationDto fromEntity(Participation participation) {
        return new ParticipationDto(participation.getId(),
                participation.getName(),
                participation.getChallengeScore().getScore());
    }

    public Long getParticipationId() {
        return participationId;
    }

    public String getName() {
        return name;
    }

    public int getChallengeScore() {
        return challengeScore;
    }
}
