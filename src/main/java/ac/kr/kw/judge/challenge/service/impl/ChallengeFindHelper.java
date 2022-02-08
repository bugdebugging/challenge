package ac.kr.kw.judge.challenge.service.impl;

import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;

public class ChallengeFindHelper {
    public static Challenge findChallengeById(Long challengeId, ChallengeRepository challengeRepository) {
        return challengeRepository.findById(challengeId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException(challengeId + "의 대회가 존재하지 않습니다.");
                });
    }
}
