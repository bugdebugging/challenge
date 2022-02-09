package ac.kr.kw.judge.challenge.service.command;

import ac.kr.kw.judge.challenge.domain.ChallengeScore;
import ac.kr.kw.judge.challenge.domain.SubmitStatus;

import static com.google.common.base.Preconditions.checkArgument;

public class CompleteGradingSubmitCommand {
    private Long submitId;
    private SubmitStatus status;
    private ChallengeScore challengeScore;

    public CompleteGradingSubmitCommand(Long submitId, SubmitStatus status, ChallengeScore challengeScore) {
        checkArgument(submitId != null, "제출 id는 필수입니다.");
        checkArgument(status != null, "submit 상태는 필수입니다.");
        checkArgument(challengeScore != null, "submit 점수는 필수입니다.");

        this.submitId = submitId;
        this.status = status;
        this.challengeScore = challengeScore;
    }

    public Long getSubmitId() {
        return submitId;
    }

    public SubmitStatus getStatus() {
        return status;
    }

    public ChallengeScore getChallengeScore() {
        return challengeScore;
    }
}
