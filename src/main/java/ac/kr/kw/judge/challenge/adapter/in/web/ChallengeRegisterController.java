package ac.kr.kw.judge.challenge.adapter.in.web;

import ac.kr.kw.judge.challenge.adapter.out.web.ProblemClient;
import ac.kr.kw.judge.challenge.dto.in.ChallengeRegisterDto;
import ac.kr.kw.judge.challenge.service.command.ChallengeRegisterCommand;
import ac.kr.kw.judge.challenge.service.command.QuestionRegisterCommand;
import ac.kr.kw.judge.challenge.service.port.in.ChallengeRegisterService;
import ac.kr.kw.judge.commons.api.ApiResult;
import ac.kr.kw.judge.commons.api.ApiUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"challenge"})
@RestController
@RequiredArgsConstructor
public class ChallengeRegisterController {
    private final ChallengeRegisterService challengeRegisterService;
    private final ProblemClient problemClient;

    @ApiOperation(value = "대회 생성", notes = "출제될 문제,출제진과 함께 대회 생성")
    @PostMapping("/api/challenges")
    public ApiResult registerChallenge(@RequestBody ChallengeRegisterDto challengeRegisterDto) {
        List<QuestionRegisterCommand> questionRegisterCommands =
                problemClient.findProblemsContainingIds(challengeRegisterDto.getQuestions())
                        .stream().map(problemDto -> new QuestionRegisterCommand(problemDto.getId(), problemDto.getName()))
                        .collect(Collectors.toList());

        ChallengeRegisterCommand challengeRegisterCommand = new ChallengeRegisterCommand(challengeRegisterDto.getAuthors(),
                questionRegisterCommands,
                challengeRegisterDto.getName(),
                challengeRegisterDto.getChallengeDateTime());

        return ApiUtils.success(challengeRegisterService.registerChallenge(challengeRegisterCommand));
    }
}
