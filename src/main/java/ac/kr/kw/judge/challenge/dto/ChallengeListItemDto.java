package ac.kr.kw.judge.challenge.dto;

import ac.kr.kw.judge.challenge.domain.Author;
import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.domain.ChallengeDateTime;

import java.util.List;

public class ChallengeListItemDto {
    private Long id;
    private String name;
    private ChallengeDateTime challengeDateTime;
    private List<Author> authors;
    private int numOfParticipation;
    private int numOfQuestion;

    public ChallengeListItemDto(Challenge challenge, int numOfParticipation, int numOfQuestion) {
        this.id = challenge.getId();
        this.name = challenge.getName();
        this.challengeDateTime = challenge.getChallengeDateTime();
        this.authors = challenge.getAuthors();
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

    public List<Author> getAuthors() {
        return authors;
    }

    public int getNumOfParticipation() {
        return numOfParticipation;
    }

    public int getNumOfQuestion() {
        return numOfQuestion;
    }
}
