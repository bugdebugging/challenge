package ac.kr.kw.judge.challenge.dto.in;

public class SubmitRequestDto {
    private Long problemId;
    private String sourceCode;
    private String programmingLanguage;

    public SubmitRequestDto() {
    }

    public Long getProblemId() {
        return problemId;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }
}
