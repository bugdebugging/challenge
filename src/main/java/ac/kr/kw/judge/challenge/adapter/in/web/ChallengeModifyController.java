package ac.kr.kw.judge.challenge.adapter.in.web;

import ac.kr.kw.judge.challenge.service.port.out.ProblemClient;
import ac.kr.kw.judge.challenge.dto.in.AuthorsModifyWrapper;
import ac.kr.kw.judge.challenge.dto.in.ChallengeInfoModifyDto;
import ac.kr.kw.judge.challenge.dto.in.QuestionModifyWrapper;
import ac.kr.kw.judge.challenge.service.command.ChallengeInfoModifyCommand;
import ac.kr.kw.judge.challenge.service.command.QuestionRegisterCommand;
import ac.kr.kw.judge.challenge.service.port.in.ChallengeModifyService;
import ac.kr.kw.judge.commons.api.ApiResult;
import ac.kr.kw.judge.commons.api.ApiUtils;
import ac.kr.kw.judge.commons.auth.AuthorizedUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"challenge"})
@RestController
@RequiredArgsConstructor
public class ChallengeModifyController {
    private final ChallengeModifyService challengeModifyService;
    private final ProblemClient problemClient;

    @ApiOperation(value = "출제문제 수정", notes = "대회에 출제될 문제 수정")
    @PutMapping("/api/challenges/{challengeId}/questions")
    public ApiResult modifyChallengeQuestions(@PathVariable("challengeId") Long challengeId,
                                              @RequestBody QuestionModifyWrapper questionModifyWrapper,
                                              @AuthorizedUser String username) {
        List<QuestionRegisterCommand> questionRegisterCommand = problemClient.findProblemsContainingIds(questionModifyWrapper.getQuestions(), username)
                .stream().map(problemDto -> new QuestionRegisterCommand(problemDto.getId(), problemDto.getName()))
                .collect(Collectors.toList());

        challengeModifyService.changeQuestions(challengeId, username, questionRegisterCommand);
        return ApiUtils.simpleMessage("You have successfully changed the questions.");
    }

    @ApiOperation(value = "대회 정보 수정", notes = "대회 정보 수정")
    @PutMapping("/api/challenges/{challengeId}/info")
    public ApiResult modifyChallengeInfo(@PathVariable("challengeId") Long challengeId,
                                         @RequestBody ChallengeInfoModifyDto challengeInfoModifyDto,
                                         @AuthorizedUser String username) {
        ChallengeInfoModifyCommand challengeInfoModifyCommand = new ChallengeInfoModifyCommand(challengeInfoModifyDto.getName(), challengeInfoModifyDto.getChallengeDateTime());
        challengeModifyService.changeChallengeInfo(challengeId, username, challengeInfoModifyCommand);
        return ApiUtils.simpleMessage("You have successfully changed the challenge info.");
    }
}
