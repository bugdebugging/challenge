package ac.kr.kw.judge.challenge.adapter.in.web;

import ac.kr.kw.judge.challenge.dto.in.QuestionModifyWrapper;
import ac.kr.kw.judge.challenge.service.command.QuestionRegisterCommand;
import ac.kr.kw.judge.challenge.service.port.in.ChallengeModifyService;
import ac.kr.kw.judge.commons.api.ApiResult;
import ac.kr.kw.judge.commons.api.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ChallengeModifyController {
    private final ChallengeModifyService challengeModifyService;

    @PutMapping("/api/challenges/{challengeId}/questions")
    public ApiResult modifyChallengeQuestions(@PathVariable("challengeId") Long challengeId,
                                              @RequestBody QuestionModifyWrapper questionModifyWrapper) {
        List<QuestionRegisterCommand> questionRegisterCommands = questionModifyWrapper.getQuestions().stream()
                .map(question -> new QuestionRegisterCommand(question.getProblemId(), question.getTitle()))
                .collect(Collectors.toList());
        challengeModifyService.changeQuestions(challengeId,questionRegisterCommands);
        return ApiUtils.simpleMessage("You have successfully changed the questions.");
    }
}
