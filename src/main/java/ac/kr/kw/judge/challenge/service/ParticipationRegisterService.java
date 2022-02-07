package ac.kr.kw.judge.challenge.service;

import ac.kr.kw.judge.challenge.service.command.ParticipationRegisterCommand;

public interface ParticipationRegisterService {
    Long participateInChallenge(ParticipationRegisterCommand participationRegisterCommand);

    void cancelParticipation(Long participationId);
}
