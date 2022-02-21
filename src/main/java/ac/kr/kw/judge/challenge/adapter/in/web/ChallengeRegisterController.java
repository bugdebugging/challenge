package ac.kr.kw.judge.challenge.adapter.in.web;

import ac.kr.kw.judge.challenge.dto.in.ChallengeRegisterDto;
import ac.kr.kw.judge.challenge.service.command.ChallengeRegisterCommand;
import ac.kr.kw.judge.challenge.service.command.QuestionRegisterCommand;
import ac.kr.kw.judge.challenge.service.port.in.ChallengeRegisterService;
import ac.kr.kw.judge.commons.api.ApiResult;
import ac.kr.kw.judge.commons.api.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ChallengeRegisterController {
    private final ChallengeRegisterService challengeRegisterService;

    @PostMapping("/api/challenges")
    public ApiResult registerChallenge(@RequestBody ChallengeRegisterDto challengeRegisterDto) {
        List<QuestionRegisterCommand> questionRegisterCommands = challengeRegisterDto.getQuestions()
                .stream()
                .map(questionRegisterDto -> new QuestionRegisterCommand(questionRegisterDto.getProblemId(), questionRegisterDto.getTitle()))
                .collect(Collectors.toList());

        ChallengeRegisterCommand challengeRegisterCommand = new ChallengeRegisterCommand(challengeRegisterDto.getAuthors(),
                questionRegisterCommands,
                challengeRegisterDto.getName(),
                challengeRegisterDto.getChallengeDateTime());

        return ApiUtils.success(challengeRegisterService.registerChallenge(challengeRegisterCommand));
    }
}
