package ac.kr.kw.judge.challenge.dto;

import ac.kr.kw.judge.challenge.domain.ChallengeDateTime;

public class ChallengeListItemDto {
    private Long id;
    private String name;
    private ChallengeDateTime challengeDateTime;
    private int numOfParticipation;
    private int numOfQuestion;

    public ChallengeListItemDto(Long id, String name, ChallengeDateTime challengeDateTime, int numOfParticipation, int numOfQuestion) {
        this.id = id;
        this.name = name;
        this.challengeDateTime = challengeDateTime;
        this.numOfParticipation = numOfParticipation;
        this.numOfQuestion = numOfQuestion;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ChallengeDateTime getChallengeDateTime() {
        return challengeDateTime;
    }

    public int getNumOfParticipation() {
        return numOfParticipation;
    }

    public int getNumOfQuestion() {
        return numOfQuestion;
    }
}
