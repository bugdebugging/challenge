package ac.kr.kw.judge.challenge.dto.in;

import ac.kr.kw.judge.challenge.domain.Author;
import ac.kr.kw.judge.challenge.domain.ChallengeDateTime;

import java.util.List;

public class ChallengeRegisterDto {
    private List<Author> authors;
    private List<Long> problemIds;
    private String name;
    private ChallengeDateTime challengeDateTime;

    public ChallengeRegisterDto() {
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Long> getProblemIds() {
        return problemIds;
    }

    public String getName() {
        return name;
    }

    public ChallengeDateTime getChallengeDateTime() {
        return challengeDateTime;
    }
}
