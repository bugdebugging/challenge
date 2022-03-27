package ac.kr.kw.judge.challenge.service.command;

import ac.kr.kw.judge.challenge.domain.ChallengeScore;
import ac.kr.kw.judge.challenge.domain.SubmitStatus;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.*;

public class CompleteGradingSubmitCommand {
    private Long submitId;
    private Long challengeId;
    private String username;
    private SubmitStatus status;
    private ChallengeScore challengeScore;

    public CompleteGradingSubmitCommand(Long submitId, Long challengeId, String username, SubmitStatus status, ChallengeScore challengeScore) {
        checkArgument(submitId != null, "제출 id는 필수입니다.");
        checkArgument(challengeId != null, "대회 id는 필수입니다.");
        checkArgument(isNotEmpty(username), "사용자이름은 필수입니다.");
        checkArgument(status != null, "submit 상태는 필수입니다.");
        checkArgument(challengeScore != null, "submit 점수는 필수입니다.");

        this.submitId = submitId;
        this.challengeId = challengeId;
        this.username = username;
        this.status = status;
        this.challengeScore = challengeScore;
    }

    public Long getSubmitId() {
        return submitId;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public String getUsername() {
        return username;
    }

    public SubmitStatus getStatus() {
        return status;
    }

    public ChallengeScore getChallengeScore() {
        return challengeScore;
    }
}
