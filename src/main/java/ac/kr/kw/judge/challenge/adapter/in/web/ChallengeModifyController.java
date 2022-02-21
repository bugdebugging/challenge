package ac.kr.kw.judge.challenge.adapter.in.web;

import ac.kr.kw.judge.challenge.dto.in.AuthorsModifyWrapper;
import ac.kr.kw.judge.challenge.dto.in.ChallengeInfoModifyDto;
import ac.kr.kw.judge.challenge.dto.in.QuestionModifyWrapper;
import ac.kr.kw.judge.challenge.service.command.ChallengeInfoModifyCommand;
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
        challengeModifyService.changeQuestions(challengeId, questionRegisterCommands);
        return ApiUtils.simpleMessage("You have successfully changed the questions.");
    }

    @PutMapping("/api/challenges/{challengeId}/authors")
    public ApiResult modifyChallengeAuthors(@PathVariable("challengeId") Long challengeId,
                                            @RequestBody AuthorsModifyWrapper authorsModifyWrapper) {
        challengeModifyService.changeAuthors(challengeId, authorsModifyWrapper.getAuthors());
        return ApiUtils.simpleMessage("You have successfully changed the authors.");
    }

    @PutMapping("/api/challenges/{challengeId}/info")
    public ApiResult modifyChallengeInfo(@PathVariable("challengeId") Long challengeId,
                                         @RequestBody ChallengeInfoModifyDto challengeInfoModifyDto) {
        ChallengeInfoModifyCommand challengeInfoModifyCommand = new ChallengeInfoModifyCommand(challengeInfoModifyDto.getName(), challengeInfoModifyDto.getChallengeDateTime());
        challengeModifyService.changeChallengeInfo(challengeId, challengeInfoModifyCommand);
        return ApiUtils.simpleMessage("You have successfully changed the challenge info.");
    }
}
