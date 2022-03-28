package ac.kr.kw.judge.challenge.service.helper;

import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;

public class ChallengeFindHelper {
    public static Challenge findById(Long challengeId, ChallengeRepository challengeRepository) {
        return challengeRepository.findById(challengeId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 대회가 존재하지 않습니다.");
                });
    }

    public static Challenge findByIdWithParticipation(Long challengeId, ChallengeRepository challengeRepository) {
        return challengeRepository.findChallengeWithParticipation(challengeId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 대회가 존재하지 않습니다.");
                });
    }
}
