package ac.kr.kw.judge.challenge.service;

import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.domain.Participation;
import ac.kr.kw.judge.challenge.domain.Submit;
import ac.kr.kw.judge.challenge.dto.out.ParticipationDto;
import ac.kr.kw.judge.challenge.dto.out.SubmitDetailDto;
import ac.kr.kw.judge.challenge.dto.out.SubmitItemDto;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import ac.kr.kw.judge.challenge.repository.ParticipationRepository;
import ac.kr.kw.judge.challenge.repository.SubmitRepository;
import ac.kr.kw.judge.challenge.service.port.in.ParticipationSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ParticipationSearchServiceImpl implements ParticipationSearchService {
    private final ChallengeRepository challengeRepository;
    private final ParticipationRepository participationRepository;
    private final SubmitRepository submitRepository;

    @Override
    public List<SubmitItemDto> findSubmitListOfParticipation(Long participationId) {
        Participation participation = participationRepository.findById(participationId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 참여자가 존재하지 않습니다.");
                });

        return submitRepository.findSubmitByParticipation(participation)
                .stream().map(submit -> SubmitItemDto.fromEntity(submit))
                .collect(Collectors.toList());
    }

    @Override
    public SubmitDetailDto findSubmitDetail(Long submitId) {
        Submit submit = submitRepository.findById(submitId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 제출이 존재하지 않습니다.");
                });
        return SubmitDetailDto.fromEntity(submit);
    }

    @Override
    public ParticipationDto findParticipationInfoOfUser(Long challengeId, Long userId) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 대회가 존재하지 않습니다.");
                });
        Participation participation = participationRepository.findParticipationByChallengeAndUserId(challenge, userId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 사용자가 참여한적이 없습니다.");
                });
        return ParticipationDto.fromEntity(participation);
    }
}
