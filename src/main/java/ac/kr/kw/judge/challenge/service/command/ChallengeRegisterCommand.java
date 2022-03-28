package ac.kr.kw.judge.challenge.service.command;

import ac.kr.kw.judge.challenge.domain.ChallengeDateTime;

import java.time.LocalDateTime;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class ChallengeRegisterCommand {
    private List<QuestionRegisterCommand> questionRegisterCommands;
    private String name;
    private ChallengeDateTime challengeDateTime;
    private String author;

    public ChallengeRegisterCommand(List<QuestionRegisterCommand> questionRegisterCommands,
                                    String name, ChallengeDateTime challengeDateTime, String author) {
        checkArgument(questionRegisterCommands != null, "출제 문항 목록은 필수입니다.");
        checkArgument(isNotEmpty(name), "대회 이름은 필수입니다.");
        checkArgument(challengeDateTime != null, "대회 시간은 필수입니다.");
        checkArgument(isNotEmpty(author), "개최자의 이름은 필수입니다.");

        checkArgument(!challengeDateTime.getStartTime().isBefore(LocalDateTime.now()), "현재 시간보다 대회 시작시간이 과거일 수는 없습니다.");
        checkArgument(!challengeDateTime.getStartTime().isAfter(challengeDateTime.getEndTime()), "시작시간이 종료시간보다 과거일 수는 없습니다.");

        this.questionRegisterCommands = questionRegisterCommands;
        this.name = name;
        this.challengeDateTime = challengeDateTime;
        this.author=author;
    }

    public List<QuestionRegisterCommand> getQuestionRegisterCommands() {
        return questionRegisterCommands;
    }

    public String getName() {
        return name;
    }

    public ChallengeDateTime getChallengeDateTime() {
        return challengeDateTime;
    }

    public String getAuthor() {
        return author;
    }
}
