package ac.kr.kw.judge.challenge.adapter.in.web;

import ac.kr.kw.judge.challenge.dto.in.ParticipationRegisterDto;
import ac.kr.kw.judge.challenge.service.command.ParticipationRegisterCommand;
import ac.kr.kw.judge.challenge.service.port.in.ParticipationRegisterService;
import ac.kr.kw.judge.commons.api.ApiResult;
import ac.kr.kw.judge.commons.api.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ParticipationController {
    private final ParticipationRegisterService participationRegisterService;

    @PostMapping("/api/challenges/{challengeId}/participations")
    public ApiResult participateInChallenge(@PathVariable("challengeId") Long challengeId,
                                            @RequestBody ParticipationRegisterDto participationRegisterDto) {
        ParticipationRegisterCommand participationRegisterCommand = new ParticipationRegisterCommand(participationRegisterDto.getUserId(),
                participationRegisterDto.getName());

        participationRegisterService.participateInChallenge(challengeId, participationRegisterCommand);
        return ApiUtils.simpleMessage("You have been successfully registered.");
    }

    @DeleteMapping("/api/challenges/{challengeId}/participations/{participationId}")
    public ApiResult cancelParticipationInChallenge(@PathVariable("challengeId") Long challengeId,
                                                    @PathVariable("participationId") Long participationId) {
        participationRegisterService.cancelParticipation(challengeId, participationId);
        return ApiUtils.simpleMessage("You have been successfully canceled.");
    }
}
