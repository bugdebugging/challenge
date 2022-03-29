package ac.kr.kw.judge.challenge.service.port.in;

import ac.kr.kw.judge.challenge.service.command.ChallengeInfoModifyCommand;
import ac.kr.kw.judge.challenge.service.command.QuestionRegisterCommand;

import java.util.List;

public interface ChallengeModifyService {
    void changeQuestions(Long challengeId, String username, List<QuestionRegisterCommand> questionRegisterCommands);

    void changeChallengeInfo(Long challengeId, String username, ChallengeInfoModifyCommand challengeInfoModifyCommand);
}
