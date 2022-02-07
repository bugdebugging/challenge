package ac.kr.kw.judge.challenge.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class ChallengeDateTime {
    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    protected ChallengeDateTime() {
    }

    private ChallengeDateTime(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static ChallengeDateTime of(LocalDateTime startTime, LocalDateTime endTime) {
        return new ChallengeDateTime(startTime, endTime);
    }

    public boolean isBeforeChallenge(LocalDateTime timestamp) {
        return startTime.isAfter(timestamp);
    }

    public boolean isOnProgressChallenge(LocalDateTime timestamp) {
        return startTime.isBefore(timestamp) && endTime.isAfter(timestamp);
    }

    public boolean isFinishedChallenge(LocalDateTime timestamp) {
        return endTime.isBefore(timestamp);
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChallengeDateTime that = (ChallengeDateTime) o;
        return Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }
}
