package ac.kr.kw.judge.challenge.service;

import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.domain.Question;
import ac.kr.kw.judge.challenge.domain.event.ProblemOpenTimeChanged;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import ac.kr.kw.judge.challenge.service.port.in.ChallengeRegisterService;
import ac.kr.kw.judge.challenge.service.command.ChallengeRegisterCommand;
import ac.kr.kw.judge.challenge.service.port.out.EventSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ChallengeRegisterServiceImpl implements ChallengeRegisterService {
    private final ChallengeRepository challengeRepository;
    private final EventSender eventSender;

    @Override
    public Long registerChallenge(ChallengeRegisterCommand challengeRegisterCommand) {
        List<Question> questions = challengeRegisterCommand.getQuestionRegisterCommands()
                .stream().map(questionRegisterCommand -> Question.of(questionRegisterCommand.getProblemId(),
                        questionRegisterCommand.getTitle()))
                .collect(Collectors.toList());
        Challenge challenge = new Challenge(challengeRegisterCommand.getName(),
                challengeRegisterCommand.getAuthors(),
                questions,
                challengeRegisterCommand.getChallengeDateTime());

        ProblemOpenTimeChanged problemOpenTimeChanged = new ProblemOpenTimeChanged(
                questions.stream().map(question -> question.getProblemId()).collect(Collectors.toList()),
                challenge.getChallengeDateTime().getStartTime());

        eventSender.publish("problem_open_time_changed", problemOpenTimeChanged);
        return challengeRepository.save(challenge).getId();
    }
}
