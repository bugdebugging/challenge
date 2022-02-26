package ac.kr.kw.judge.challenge.adapter.in.web;

import ac.kr.kw.judge.challenge.dto.out.ChallengeListItemDto;
import ac.kr.kw.judge.challenge.dto.out.ParticipationDto;
import ac.kr.kw.judge.challenge.dto.out.QuestionDto;
import ac.kr.kw.judge.challenge.service.port.in.ChallengeSearchService;
import ac.kr.kw.judge.commons.api.ApiResult;
import ac.kr.kw.judge.commons.api.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChallengeSearchController {
    private final ChallengeSearchService challengeSearchService;

    @GetMapping("/api/challenges")
    public ApiResult<List<ChallengeListItemDto>> findChallenges(@RequestParam(required = false, defaultValue = "0") int page,
                                                                @RequestParam(required = false, defaultValue = "10") int limit) {
        return ApiUtils.success(challengeSearchService.findChallengeSummaries(page, limit));
    }

    @GetMapping("/api/challenges/{challengeId}/questions")
    public ApiResult<List<QuestionDto>> findQuestionsOfChallenge(@PathVariable("challengeId") Long challengeId){
        return ApiUtils.success(challengeSearchService.findQuestionsOfChallenge(challengeId));
    }

    @GetMapping("/api/challenges/{challengeId}/participations")
    public ApiResult<List<ParticipationDto>>findParticipationsOfChallenge(@PathVariable("challengeId")Long challengeId,
                                                                          @RequestParam(required = false, defaultValue = "0") int page,
                                                                          @RequestParam(required = false, defaultValue = "10") int limit){
        return ApiUtils.success(challengeSearchService.findParticipationsOfChallenge(challengeId,page,limit));
    }
}
