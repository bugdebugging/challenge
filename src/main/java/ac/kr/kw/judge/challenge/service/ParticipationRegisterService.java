package ac.kr.kw.judge.challenge.service;

import ac.kr.kw.judge.challenge.service.command.ParticipationRegisterCommand;

public interface ParticipationRegisterService {
    void participateInChallenge(Long challengeId, ParticipationRegisterCommand participationRegisterCommand);

    void cancelParticipation(Long challengeId, Long userId);
}
