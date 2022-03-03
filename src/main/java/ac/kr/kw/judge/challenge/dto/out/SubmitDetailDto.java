package ac.kr.kw.judge.challenge.dto.out;

import ac.kr.kw.judge.challenge.domain.Submit;

public class SubmitDetailDto {
    private Long id;
    private Long problemId;
    private String programmingLanguage;
    private String sourceCode;

    private SubmitDetailDto(Long id, Long problemId, String programmingLanguage, String sourceCode) {
        this.id = id;
        this.problemId = problemId;
        this.programmingLanguage = programmingLanguage;
        this.sourceCode = sourceCode;
    }

    public static SubmitDetailDto fromEntity(Submit submit) {
        return new SubmitDetailDto(submit.getId()
                , submit.getProblemId()
                , submit.getProgrammingLanguage().getValue()
                , submit.getSourceCode());
    }

    public Long getId() {
        return id;
    }

    public Long getProblemId() {
        return problemId;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public String getSourceCode() {
        return sourceCode;
    }
}
