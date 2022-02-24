package ac.kr.kw.judge.challenge.adapter.in.web;

import ac.kr.kw.judge.challenge.dto.in.ParticipationRegisterDto;
import ac.kr.kw.judge.challenge.service.command.ParticipationRegisterCommand;
import ac.kr.kw.judge.challenge.service.port.in.ParticipationRegisterService;
import ac.kr.kw.judge.commons.api.ApiResult;
import ac.kr.kw.judge.commons.api.ApiUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"challenge"})
@RestController
@RequiredArgsConstructor
public class ParticipationController {
    private final ParticipationRegisterService participationRegisterService;

    @ApiOperation(value = "대회 참여", notes = "대회에 참여")
    @PostMapping("/api/challenges/{challengeId}/participations")
    public ApiResult participateInChallenge(@PathVariable("challengeId") Long challengeId,
                                            @RequestBody ParticipationRegisterDto participationRegisterDto) {
        ParticipationRegisterCommand participationRegisterCommand = new ParticipationRegisterCommand(participationRegisterDto.getUserId(),
                participationRegisterDto.getName());

        participationRegisterService.participateInChallenge(challengeId, participationRegisterCommand);
        return ApiUtils.simpleMessage("You have been successfully registered.");
    }

    @ApiOperation(value = "대회 참여 취소", notes = "대회에 참여 취소")
    @DeleteMapping("/api/challenges/{challengeId}/participations/{participationId}")
    public ApiResult cancelParticipationInChallenge(@PathVariable("challengeId") Long challengeId,
                                                    @PathVariable("participationId") Long participationId) {
        participationRegisterService.cancelParticipation(challengeId, participationId);
        return ApiUtils.simpleMessage("You have been successfully canceled.");
    }
}
