package ac.kr.kw.judge.challenge.dto;

import ac.kr.kw.judge.challenge.domain.Challenge;

import java.util.List;
import java.util.stream.Collectors;

public class ChallengeQuestionDto {
    private List<QuestionDto> questions;

    public ChallengeQuestionDto(Challenge challenge) {
        this.questions = challenge.getChallengeQuestions()
                .stream().map(question -> QuestionDto.fromEntity(question))
                .collect(Collectors.toList());
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }
}
