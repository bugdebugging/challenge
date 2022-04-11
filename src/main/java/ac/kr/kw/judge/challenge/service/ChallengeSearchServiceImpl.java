package ac.kr.kw.judge.challenge.service;

import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.dto.out.ChallengeListItemDto;
import ac.kr.kw.judge.challenge.dto.out.ParticipationDto;
import ac.kr.kw.judge.challenge.dto.out.QuestionDto;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import ac.kr.kw.judge.challenge.repository.ParticipationRepository;
import ac.kr.kw.judge.challenge.repository.QuestionRepository;
import ac.kr.kw.judge.challenge.service.helper.ChallengeFindHelper;
import ac.kr.kw.judge.challenge.service.port.in.ChallengeSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChallengeSearchServiceImpl implements ChallengeSearchService {
    private final ChallengeRepository challengeRepository;
    private final QuestionRepository questionRepository;
    private final ParticipationRepository participationRepository;

    @Override
    public List<ChallengeListItemDto> findChallengeSummaries(int page, int limit) {
        return challengeRepository.findChallengeWithCountOfQuestionAndParticipation(PageRequest.of(page, limit, Sort.by("challengeDateTime.startTime").ascending()))
                .stream().map(objects -> new ChallengeListItemDto((Challenge) objects[0], (int) objects[1], (int) objects[2]))
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionDto> findQuestionsOfChallenge(Long challengeId) {
        Challenge challenge = ChallengeFindHelper.findById(challengeId, challengeRepository);
        return questionRepository.findQuestionByChallenge(challenge)
                .stream().map(question -> QuestionDto.fromEntity(question))
                .collect(Collectors.toList());
    }

    @Override
    public List<ParticipationDto> findParticipationsOfChallenge(Long challengeId, int page, int limit) {
        Challenge challenge = ChallengeFindHelper.findById(challengeId, challengeRepository);
        return participationRepository.findParticipationByChallenge(challenge, PageRequest.of(page, limit, Sort.by("score").descending()))
                .stream().map(participation -> ParticipationDto.fromEntity(participation))
                .collect(Collectors.toList());
    }
}
