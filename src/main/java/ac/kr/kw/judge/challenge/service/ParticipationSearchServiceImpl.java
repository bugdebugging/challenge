package ac.kr.kw.judge.challenge.service;

import ac.kr.kw.judge.challenge.domain.Participation;
import ac.kr.kw.judge.challenge.domain.Submit;
import ac.kr.kw.judge.challenge.dto.out.SubmitItemDto;
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
    public SubmitItemDto findSubmitDetail(Long submitId) {
        Submit submit = submitRepository.findById(submitId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 id의 제출이 존재하지 않습니다.");
                });
        return SubmitItemDto.fromEntity(submit);
    }
}
