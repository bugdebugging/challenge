package ac.kr.kw.judge.challenge.service.helper;

import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.domain.Participation;
import ac.kr.kw.judge.challenge.repository.ParticipationRepository;

public class ParticipationFindHelper {
    public static Participation findByChallengeAndName(Challenge challenge, String username, ParticipationRepository participationRepository) {
        return participationRepository.findParticipationByChallengeAndName(challenge, username)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 참여자가 존재하지 않습니다.");
                });
    }
}
