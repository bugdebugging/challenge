package ac.kr.kw.judge.challenge.service.port.in;

import ac.kr.kw.judge.challenge.dto.out.ChallengeListItemDto;

import java.util.List;

public interface ChallengeSearchService {
    List<ChallengeListItemDto> findChallengeSummaries(int page, int limit);
}
