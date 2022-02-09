package ac.kr.kw.judge.challenge.service.impl;

import ac.kr.kw.judge.challenge.domain.Participation;
import ac.kr.kw.judge.challenge.repository.ParticipationRepository;

import static com.google.common.base.Preconditions.checkArgument;

public class ParticipationFindHelper {
    public static Participation findParticipationById(Long participationId, ParticipationRepository participationRepository) {
        checkArgument(participationId != null, "참여자 id는 필수입니다.");
        return participationRepository.findById(participationId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 참여자가 존재하지 않습니다.");
                });
    }
}
