package ac.kr.kw.judge.challenge.adapter.in.web;

import ac.kr.kw.judge.challenge.dto.out.SubmitDetailDto;
import ac.kr.kw.judge.challenge.dto.out.SubmitItemDto;
import ac.kr.kw.judge.challenge.service.port.in.ParticipationSearchService;
import ac.kr.kw.judge.commons.api.ApiResult;
import ac.kr.kw.judge.commons.api.ApiUtils;
import ac.kr.kw.judge.commons.auth.AuthorizedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParticipationSearchController {
    private final ParticipationSearchService participationSearchService;

    @GetMapping("/api/challenges/{challengeId}/participations/submits")
    public ApiResult<List<SubmitItemDto>> searchSubmitOfParticipation(@PathVariable("challengeId") Long challengeId,
                                                                      @AuthorizedUser String username) {
        return ApiUtils.success(participationSearchService.findSubmitListOfParticipation(challengeId, username));
    }

    @GetMapping("/api/challenges/{challengeId}/participations/submits/{submitId}")
    public ApiResult<SubmitDetailDto> searchSubmitDetail(@PathVariable("challengeId") Long challengeId,
                                                         @AuthorizedUser String username,
                                                         @PathVariable("submitId") Long submitId) {
        return ApiUtils.success(participationSearchService.findSubmitDetail(challengeId, username, submitId));
    }
}
