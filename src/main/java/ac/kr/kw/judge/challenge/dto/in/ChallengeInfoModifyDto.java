package ac.kr.kw.judge.challenge.dto.in;

import ac.kr.kw.judge.challenge.domain.ChallengeDateTime;

public class ChallengeInfoModifyDto {
    private String name;
    private ChallengeDateTime challengeDateTime;

    public ChallengeInfoModifyDto() {
    }

    public String getName() {
        return name;
    }

    public ChallengeDateTime getChallengeDateTime() {
        return challengeDateTime;
    }
}
