package ac.kr.kw.judge.challenge.dto.out;

import ac.kr.kw.judge.challenge.domain.Participation;

public class ParticipationDto {
    private Long participationId;
    private Long userId;
    private String name;
    private int challengeScore;

    private ParticipationDto(Long participationId, Long userId, String name, int challengeScore) {
        this.participationId = participationId;
        this.userId = userId;
        this.name = name;
        this.challengeScore = challengeScore;
    }

    public static ParticipationDto fromEntity(Participation participation) {
        return new ParticipationDto(participation.getId(),
                participation.getUserId(),
                participation.getName(),
                participation.getChallengeScore().getScore());
    }

    public Long getParticipationId() {
        return participationId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getChallengeScore() {
        return challengeScore;
    }
}
