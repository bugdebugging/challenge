package ac.kr.kw.judge.challenge.service.impl;

import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import ac.kr.kw.judge.challenge.service.ParticipationRegisterService;
import ac.kr.kw.judge.challenge.service.command.ParticipationRegisterCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ParticipationRegisterServiceImpl implements ParticipationRegisterService {
    private final ChallengeRepository challengeRepository;

    @Override
    public void participateInChallenge(Long challengeId, ParticipationRegisterCommand participationRegisterCommand) {
        Challenge challenge = ChallengeFindHelper.findChallengeById(challengeId, challengeRepository);
        challenge.participateInChallenge(participationRegisterCommand.getUserId(), participationRegisterCommand.getName());
    }

    @Override
    public void cancelParticipation(Long challengeId, Long userId) {
        Challenge challenge = ChallengeFindHelper.findChallengeById(challengeId, challengeRepository);
        challenge.cancelParticipate(userId);
    }
}
