package ac.kr.kw.judge.challenge.dto.out;

import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.domain.ChallengeDateTime;

public class ChallengeListItemDto {
    private Long id;
    private String name;
    private ChallengeDateTime challengeDateTime;
    private int numOfParticipation;
    private int numOfQuestion;

    public ChallengeListItemDto(Challenge challenge, int numOfParticipation, int numOfQuestion) {
        this.id = challenge.getId();
        this.name = challenge.getName();
        this.challengeDateTime = challenge.getChallengeDateTime();
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
