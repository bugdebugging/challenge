package ac.kr.kw.judge.challenge.dto.in;

public class QuestionRegisterDto {
    private Long problemId;
    private String title;

    public QuestionRegisterDto() {
    }

    public Long getProblemId() {
        return problemId;
    }

    public String getTitle() {
        return title;
    }
}
