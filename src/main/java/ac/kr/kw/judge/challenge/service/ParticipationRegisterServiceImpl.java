package ac.kr.kw.judge.challenge.service;

import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import ac.kr.kw.judge.challenge.service.helper.ChallengeFindHelper;
import ac.kr.kw.judge.challenge.service.port.in.ParticipationRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Service
@Transactional
@RequiredArgsConstructor
public class ParticipationRegisterServiceImpl implements ParticipationRegisterService {
    private final ChallengeRepository challengeRepository;

    @Override
    public void participateInChallenge(Long challengeId, String username) {
        checkArgument(isNotEmpty(username), "사용자 이름은 필수입니다.");
        Challenge challenge = ChallengeFindHelper.findByIdWithParticipation(challengeId, challengeRepository);
        challenge.participateInChallenge(username, LocalDateTime.now());
    }

    @Override
    public void cancelParticipation(Long challengeId, String username) {
        Challenge challenge = ChallengeFindHelper.findByIdWithParticipation(challengeId, challengeRepository);
        challenge.cancelParticipate(username);
    }
}
