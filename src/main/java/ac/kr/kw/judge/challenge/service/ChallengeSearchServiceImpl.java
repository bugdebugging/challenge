package ac.kr.kw.judge.challenge.service;

import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.dto.out.ChallengeListItemDto;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import ac.kr.kw.judge.challenge.service.port.in.ChallengeSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChallengeSearchServiceImpl implements ChallengeSearchService {
    private final ChallengeRepository challengeRepository;

    @Override
    public List<ChallengeListItemDto> findChallengeSummaries(int page, int limit) {
        return challengeRepository.findChallengeWithCountOfQuestionAndParticipation(PageRequest.of(page, limit))
                .stream().map(objects -> new ChallengeListItemDto((Challenge) objects[0], (int) objects[1], (int) objects[2]))
                .collect(Collectors.toList());
    }
}
