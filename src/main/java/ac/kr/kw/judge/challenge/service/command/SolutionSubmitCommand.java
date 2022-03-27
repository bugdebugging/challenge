package ac.kr.kw.judge.challenge.service.command;

import ac.kr.kw.judge.challenge.domain.ProgrammingLanguage;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class SolutionSubmitCommand {
    private Long participationId;
    private Long problemId;
    private ProgrammingLanguage programmingLanguage;
    private String sourceCode;
    private String username;

    public SolutionSubmitCommand(Long participationId, Long problemId, ProgrammingLanguage programmingLanguage, String sourceCode, String username) {
        checkArgument(participationId != null, "참여자 id는 필수입니다.");
        checkArgument(problemId != null, "문제 id는 필수입니다.");
        checkArgument(programmingLanguage != null, "제출 언어는 필수입니다.");
        checkArgument(isNotEmpty(sourceCode), "소스코드는 빈문자열일 수 없습니다.");
        checkArgument(isNotEmpty(username), "참여자 이름은 빈문자열일 수 없습니다.");

        this.participationId = participationId;
        this.problemId = problemId;
        this.programmingLanguage = programmingLanguage;
        this.sourceCode = sourceCode;
        this.username = username;
    }

    public Long getParticipationId() {
        return participationId;
    }

    public Long getProblemId() {
        return problemId;
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public String getUsername() {
        return username;
    }
}
