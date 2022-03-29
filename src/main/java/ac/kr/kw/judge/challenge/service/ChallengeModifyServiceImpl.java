package ac.kr.kw.judge.challenge.service;

import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.domain.Question;
import ac.kr.kw.judge.challenge.domain.event.ProblemOpenTimeChanged;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import ac.kr.kw.judge.challenge.service.command.ChallengeInfoModifyCommand;
import ac.kr.kw.judge.challenge.service.command.QuestionRegisterCommand;
import ac.kr.kw.judge.challenge.service.helper.ChallengeFindHelper;
import ac.kr.kw.judge.challenge.service.port.in.ChallengeModifyService;
import ac.kr.kw.judge.challenge.service.port.out.EventSender;
import ac.kr.kw.judge.commons.exception.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ChallengeModifyServiceImpl implements ChallengeModifyService {
    private final ChallengeRepository challengeRepository;
    private final EventSender eventSender;

    @Override
    public void changeQuestions(Long challengeId, String username, List<QuestionRegisterCommand> questionRegisterCommands) {
        Challenge challenge = ChallengeFindHelper.findById(challengeId, challengeRepository);
        if (challenge.getAuthor().equals(username)){
            throw new UnAuthorizedException("대회 개최자만 문제를 수정 할 수 있습니다.");
        }
            List<Question> questions = questionRegisterCommands.stream()
                    .map(question -> question.toEntity()).collect(Collectors.toList());
        challenge.changeQuestions(questions);
    }

    @Override
    public void changeChallengeInfo(Long challengeId, String username, ChallengeInfoModifyCommand challengeInfoModifyCommand) {
        Challenge challenge = ChallengeFindHelper.findById(challengeId, challengeRepository);
        if (challenge.getAuthor().equals(username)){
            throw new UnAuthorizedException("대회 개최자만 문제를 정보들을 수정 할 수 있습니다.");
        }

        challenge.changeChallengeInfo(challengeInfoModifyCommand.getName(), challengeInfoModifyCommand.getChallengeDateTime());

        ProblemOpenTimeChanged problemOpenTimeChanged = new ProblemOpenTimeChanged(
                challenge.getChallengeQuestions()
                        .stream().map(question -> question.getProblemId())
                        .collect(Collectors.toList()),
                challenge.getChallengeDateTime().getStartTime());
        eventSender.publish("problem_open_time_changed", problemOpenTimeChanged);
    }
}
