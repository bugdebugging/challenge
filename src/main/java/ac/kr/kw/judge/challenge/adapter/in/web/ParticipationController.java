package ac.kr.kw.judge.challenge.adapter.in.web;

import ac.kr.kw.judge.challenge.service.port.in.ParticipationRegisterService;
import ac.kr.kw.judge.commons.api.ApiResult;
import ac.kr.kw.judge.commons.api.ApiUtils;
import ac.kr.kw.judge.commons.auth.AuthorizedUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"challenge"})
@RestController
@RequiredArgsConstructor
public class ParticipationController {
    private final ParticipationRegisterService participationRegisterService;

    @ApiOperation(value = "대회 참여", notes = "대회에 참여")
    @PostMapping("/api/challenges/{challengeId}/participations")
    public ApiResult participateInChallenge(@PathVariable("challengeId") Long challengeId,
                                            @AuthorizedUser String username) {
        participationRegisterService.participateInChallenge(challengeId, username);
        return ApiUtils.simpleMessage("You have been successfully registered.");
    }

    @ApiOperation(value = "대회 참여 취소", notes = "대회에 참여 취소")
    @DeleteMapping("/api/challenges/{challengeId}/participations")
    public ApiResult cancelParticipationInChallenge(@PathVariable("challengeId") Long challengeId,
                                                    @AuthorizedUser String username) {
        participationRegisterService.cancelParticipation(challengeId, username);
        return ApiUtils.simpleMessage("You have been successfully canceled.");
    }
}
