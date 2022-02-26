package ac.kr.kw.judge.challenge.dto.out;

import ac.kr.kw.judge.challenge.domain.Participation;

public class ParticipationDto {
    private Long id;
    private Long userId;
    private String name;
    private int challengeScore;

    private ParticipationDto(Long id, Long userId, String name, int challengeScore) {
        this.id = id;
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

    public Long getId() {
        return id;
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
