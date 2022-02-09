package ac.kr.kw.judge.challenge.service.impl;

import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;

import static com.google.common.base.Preconditions.checkArgument;

public class ChallengeFindHelper {
    public static Challenge findChallengeById(Long challengeId, ChallengeRepository challengeRepository) {
        checkArgument(challengeId != null, "대회 id는 필수입니다.");
        return challengeRepository.findById(challengeId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException(challengeId + "의 대회가 존재하지 않습니다.");
                });
    }
}
