package ac.kr.kw.judge.challenge.adapter.in.web;

import ac.kr.kw.judge.challenge.dto.out.ParticipationDto;
import ac.kr.kw.judge.challenge.dto.out.SubmitDetailDto;
import ac.kr.kw.judge.challenge.dto.out.SubmitItemDto;
import ac.kr.kw.judge.challenge.service.port.in.ParticipationSearchService;
import ac.kr.kw.judge.commons.api.ApiResult;
import ac.kr.kw.judge.commons.api.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParticipationSearchController {
    private final ParticipationSearchService participationSearchService;

    @GetMapping("/api/challenges/{challengeId}/participations/{participationId}/submits")
    public ApiResult<List<SubmitItemDto>> searchSubmitOfParticipation(@PathVariable("challengeId") Long challengeId,
                                                                      @PathVariable("participationId") Long participationId) {
        return ApiUtils.success(participationSearchService.findSubmitListOfParticipation(participationId));
    }

    @GetMapping("/api/challenges/{challengeId}/participations/{participationId}/submits/{submitId}")
    public ApiResult<SubmitDetailDto> searchSubmitDetail(@PathVariable("challengeId") Long challengeId,
                                                         @PathVariable("participationId") Long participationId,
                                                         @PathVariable("submitId") Long submitId) {
        return ApiUtils.success(participationSearchService.findSubmitDetail(submitId));
    }

    @GetMapping("/api/challenges/{challengeId}/users/{userId}")
    public ApiResult<ParticipationDto> searchParticipationInfoOfUser(@PathVariable("challengeId") Long challengeId,
                                                                     @PathVariable("userId") Long userId) {
        return ApiUtils.success(participationSearchService.findParticipationInfoOfUser(challengeId, userId));
    }
}
