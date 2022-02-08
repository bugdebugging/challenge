package ac.kr.kw.judge.challenge.service.command;

import ac.kr.kw.judge.challenge.domain.ChallengeDateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class ChallengeInfoModifyCommand {
    private String name;
    private ChallengeDateTime challengeDateTime;

    public ChallengeInfoModifyCommand(String name, ChallengeDateTime challengeDateTime) {
        checkArgument(isNotEmpty(name), "수정할 대회 이름은 필수입니다.");
        checkArgument(challengeDateTime != null, "수정할 대회 기간은 필수입니다.");

        this.name = name;
        this.challengeDateTime = challengeDateTime;
    }

    public String getName() {
        return name;
    }

    public ChallengeDateTime getChallengeDateTime() {
        return challengeDateTime;
    }
}
