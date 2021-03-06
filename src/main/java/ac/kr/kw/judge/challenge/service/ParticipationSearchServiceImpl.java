package ac.kr.kw.judge.challenge.service;

import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.domain.Participation;
import ac.kr.kw.judge.challenge.domain.Submit;
import ac.kr.kw.judge.challenge.dto.out.SubmitDetailDto;
import ac.kr.kw.judge.challenge.dto.out.SubmitItemDto;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import ac.kr.kw.judge.challenge.repository.ParticipationRepository;
import ac.kr.kw.judge.challenge.repository.SubmitRepository;
import ac.kr.kw.judge.challenge.service.helper.ChallengeFindHelper;
import ac.kr.kw.judge.challenge.service.helper.ParticipationFindHelper;
import ac.kr.kw.judge.challenge.service.port.in.ParticipationSearchService;
import ac.kr.kw.judge.commons.exception.UnAuthorizedException;
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
    public List<SubmitItemDto> findSubmitListOfParticipation(Long challengeId, String username) {
        Challenge challenge = ChallengeFindHelper.findById(challengeId, challengeRepository);
        Participation participation = ParticipationFindHelper.findByChallengeAndName(challenge, username, participationRepository);

        return submitRepository.findSubmitByParticipation(participation)
                .stream().map(submit -> SubmitItemDto.fromEntity(submit))
                .collect(Collectors.toList());
    }

    @Override
    public SubmitDetailDto findSubmitDetail(Long challengeId, String username, Long submitId) {
        Submit submit = submitRepository.findById(submitId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("?????? id??? ????????? ???????????? ????????????.");
                });
        if (!submit.getParticipation().getName().equals(username)) {
            throw new UnAuthorizedException("????????? ????????? ????????? ????????? ??? ????????????.");
        }
        return SubmitDetailDto.fromEntity(submit);
    }
}
