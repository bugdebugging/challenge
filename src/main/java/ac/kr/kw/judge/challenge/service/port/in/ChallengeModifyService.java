package ac.kr.kw.judge.challenge.service.port.in;

import ac.kr.kw.judge.challenge.service.command.ChallengeInfoModifyCommand;
import ac.kr.kw.judge.challenge.service.command.QuestionRegisterCommand;

import java.util.List;

public interface ChallengeModifyService {
    void changeQuestions(Long challengeId, List<QuestionRegisterCommand> questionRegisterCommands);

    void changeChallengeInfo(Long challengeId, ChallengeInfoModifyCommand challengeInfoModifyCommand);
}
