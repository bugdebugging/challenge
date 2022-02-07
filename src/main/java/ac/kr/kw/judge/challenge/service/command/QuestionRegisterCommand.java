package ac.kr.kw.judge.challenge.service.command;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class QuestionRegisterCommand {
    private Long problemId;
    private String title;

    public QuestionRegisterCommand(Long problemId, String title) {
        checkArgument(problemId != null, "출제할 문제 id는 필수입니다.");
        checkArgument(isNotEmpty(title), "출제할 문제의 제목은 필수입니다.");

        this.problemId = problemId;
        this.title = title;
    }

    public Long getProblemId() {
        return problemId;
    }

    public String getTitle() {
        return title;
    }
}
