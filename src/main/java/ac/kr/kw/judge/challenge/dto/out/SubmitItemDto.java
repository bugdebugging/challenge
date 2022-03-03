package ac.kr.kw.judge.challenge.dto.out;

import ac.kr.kw.judge.challenge.domain.Submit;
import ac.kr.kw.judge.challenge.domain.SubmitStatus;

import java.time.LocalDateTime;

public class SubmitItemDto {
    private Long id;
    private Long problemId;
    private LocalDateTime submittedAt;
    private int challengeScore;
    private SubmitStatus status;

    private SubmitItemDto(Long id, Long problemId, LocalDateTime submittedAt, int challengeScore, SubmitStatus status) {
        this.id = id;
        this.problemId = problemId;
        this.submittedAt = submittedAt;
        this.challengeScore = challengeScore;
        this.status = status;
    }

    public static SubmitItemDto fromEntity(Submit submit) {
        return new SubmitItemDto(submit.getId(), submit.getProblemId(), submit.getSubmittedAt(), submit.getScore().getScore(), submit.getSubmitStatus());
    }

    public Long getId() {
        return id;
    }

    public Long getProblemId() {
        return problemId;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public int getChallengeScore() {
        return challengeScore;
    }

    public SubmitStatus getStatus() {
        return status;
    }
}
