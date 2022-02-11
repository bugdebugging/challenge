package ac.kr.kw.judge.challenge.dto;

import ac.kr.kw.judge.challenge.domain.Question;

public class QuestionDto {
    private Long id;
    private Long problemId;
    private String title;

    private QuestionDto(Long id, Long problemId, String title) {
        this.id = id;
        this.problemId = problemId;
        this.title = title;
    }

    public static QuestionDto fromEntity(Question question) {
        return new QuestionDto(question.getId(), question.getProblemId(), question.getTitle());
    }

    public Long getId() {
        return id;
    }

    public Long getProblemId() {
        return problemId;
    }

    public String getTitle() {
        return title;
    }
}
