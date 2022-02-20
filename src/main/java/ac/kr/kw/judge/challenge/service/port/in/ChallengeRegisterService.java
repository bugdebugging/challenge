package ac.kr.kw.judge.challenge.service.port.in;

import ac.kr.kw.judge.challenge.service.command.ChallengeRegisterCommand;

public interface ChallengeRegisterService {
    Long registerChallenge(ChallengeRegisterCommand challengeRegisterCommand);
}
