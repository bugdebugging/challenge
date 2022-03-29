package ac.kr.kw.judge.challenge.dto.in;

import ac.kr.kw.judge.challenge.domain.ChallengeDateTime;

import java.util.List;

public class ChallengeRegisterDto {
    private List<Long> questions;
    private String name;
    private ChallengeDateTime challengeDateTime;

    public ChallengeRegisterDto() {
    }

    public List<Long> getQuestions() {
        return questions;
    }

    public String getName() {
        return name;
    }

    public ChallengeDateTime getChallengeDateTime() {
        return challengeDateTime;
    }
}
