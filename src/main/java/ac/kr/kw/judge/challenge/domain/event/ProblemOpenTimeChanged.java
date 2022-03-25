package ac.kr.kw.judge.challenge.domain.event;

import java.time.LocalDateTime;
import java.util.List;

public class ProblemOpenTimeChanged {
    private List<Long> problemIds;
    private LocalDateTime openTime;

    public ProblemOpenTimeChanged(List<Long> problemIds, LocalDateTime openTime) {
        this.problemIds = problemIds;
        this.openTime = openTime;
    }

    public List<Long> getProblemIds() {
        return problemIds;
    }

    public LocalDateTime getOpenTime() {
        return openTime;
    }
}
