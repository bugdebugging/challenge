package ac.kr.kw.judge.challenge.service;

import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.domain.Question;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import ac.kr.kw.judge.challenge.service.port.in.ChallengeRegisterService;
import ac.kr.kw.judge.challenge.service.command.ChallengeRegisterCommand;
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
        return challengeRepository.save(challenge).getId();
    }
}
