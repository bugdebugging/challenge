package ac.kr.kw.judge.challenge.service.command;

import ac.kr.kw.judge.challenge.domain.Author;
import ac.kr.kw.judge.challenge.domain.ChallengeDateTime;

import java.time.LocalDateTime;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class ChallengeRegisterCommand {
    private List<Author> authors;
    private List<QuestionRegisterCommand> questionRegisterCommands;
    private String name;
    private ChallengeDateTime challengeDateTime;

    public ChallengeRegisterCommand(List<Author> authors, List<QuestionRegisterCommand> questionRegisterCommands,
                                    String name,
                                    ChallengeDateTime challengeDateTime) {
        checkArgument(authors != null, "저자 목록은 필수입니다.");
        checkArgument(questionRegisterCommands != null, "출제 문항 목록은 필수입니다.");
        checkArgument(isNotEmpty(name), "대회 이름은 필수입니다.");
        checkArgument(challengeDateTime != null, "대회 시간은 필수입니다.");

        checkArgument(!challengeDateTime.getStartTime().isBefore(LocalDateTime.now()),"현재 시간보다 대회 시작시간이 과거일 수는 없습니다.");
        checkArgument(!challengeDateTime.getStartTime().isAfter(challengeDateTime.getEndTime()),"시작시간이 종료시간보다 과거일 수는 없습니다.");

        this.authors = authors;
        this.questionRegisterCommands = questionRegisterCommands;
        this.name = name;
        this.challengeDateTime = challengeDateTime;
    }

    public List<Author> getAuthors() {
        return authors;
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
}
