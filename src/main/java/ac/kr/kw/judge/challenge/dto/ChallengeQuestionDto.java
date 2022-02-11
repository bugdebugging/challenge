package ac.kr.kw.judge.challenge.dto;

import java.util.List;

public class ChallengeQuestionDto {
    private List<QuestionDto> questions;

    public ChallengeQuestionDto(List<QuestionDto> questions) {
        this.questions = questions;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }
}
