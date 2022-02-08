package ac.kr.kw.judge.challenge.service;

import ac.kr.kw.judge.challenge.domain.Author;
import ac.kr.kw.judge.challenge.service.command.ChallengeInfoModifyCommand;
import ac.kr.kw.judge.challenge.service.command.QuestionRegisterCommand;

import java.util.List;

public interface ChallengeModifyService {
    void changeQuestions(Long challengeId, List<QuestionRegisterCommand> questionRegisterCommands);

    void changeAuthors(Long challengeId, List<Author> authors);

    void changeChallengeInfo(Long challengeId, ChallengeInfoModifyCommand challengeInfoModifyCommand);
}
