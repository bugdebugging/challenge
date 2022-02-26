package ac.kr.kw.judge.challenge.service.port.in;

import ac.kr.kw.judge.challenge.dto.out.ChallengeListItemDto;
import ac.kr.kw.judge.challenge.dto.out.QuestionDto;

import java.util.List;

public interface ChallengeSearchService {
    List<ChallengeListItemDto> findChallengeSummaries(int page, int limit);

    List<QuestionDto> findQuestionsOfChallenge(Long challengeId);
}
