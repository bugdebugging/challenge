package ac.kr.kw.judge.challenge.adapter.in.web;

import ac.kr.kw.judge.challenge.domain.ProgrammingLanguage;
import ac.kr.kw.judge.challenge.dto.in.SubmitRequestDto;
import ac.kr.kw.judge.challenge.service.command.SolutionSubmitCommand;
import ac.kr.kw.judge.challenge.service.port.in.SubmitSolutionService;
import ac.kr.kw.judge.commons.api.ApiResult;
import ac.kr.kw.judge.commons.api.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubmitSolutionController {
    private final SubmitSolutionService submitSolutionService;

    @PostMapping("/api/challenges/{challengeId}/participations/{participationId}/submits")
    public ApiResult submitSolution(@PathVariable("challengeId") Long challengeId,
                                    @PathVariable("participationId") Long participationId,
                                    @RequestBody SubmitRequestDto submitRequestDto) {

        SolutionSubmitCommand solutionSubmitCommand = new SolutionSubmitCommand(participationId
                , submitRequestDto.getProblemId()
                , ProgrammingLanguage.ofSupportedLanguage(submitRequestDto.getProgrammingLanguage())
                , submitRequestDto.getSourceCode());
        submitSolutionService.submitSolution(challengeId, solutionSubmitCommand);
        return ApiUtils.simpleMessage("successfully submit your code.");
    }
}
