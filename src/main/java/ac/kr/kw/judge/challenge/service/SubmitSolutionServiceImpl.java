package ac.kr.kw.judge.challenge.service;

import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.domain.Participation;
import ac.kr.kw.judge.challenge.domain.event.Submitted;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import ac.kr.kw.judge.challenge.repository.ParticipationRepository;
import ac.kr.kw.judge.challenge.service.port.in.SubmitSolutionService;
import ac.kr.kw.judge.challenge.service.command.CompleteGradingSubmitCommand;
import ac.kr.kw.judge.challenge.service.command.SolutionSubmitCommand;
import ac.kr.kw.judge.challenge.service.port.out.EventSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubmitSolutionServiceImpl implements SubmitSolutionService {
    private final ChallengeRepository challengeRepository;
    private final ParticipationRepository participationRepository;
    private final EventSender eventSender;

    @Override
    public void submitSolution(Long challengeId, SolutionSubmitCommand solutionSubmitCommand) {
        Challenge challenge = ChallengeFindHelper.findChallengeById(challengeId, challengeRepository);
        challenge.submitSolutionOfQuestion(solutionSubmitCommand.getParticipationId()
                , solutionSubmitCommand.getProblemId()
                , solutionSubmitCommand.getProgrammingLanguage()
                , solutionSubmitCommand.getSourceCode());

        eventSender.publish("submit", Submitted.of(solutionSubmitCommand.getProblemId(),
                solutionSubmitCommand.getParticipationId(),
                solutionSubmitCommand.getProgrammingLanguage().getValue(),
                solutionSubmitCommand.getSourceCode()));
    }

    @Override
    public void completeGradingOfSubmit(Long participationId, CompleteGradingSubmitCommand completeGradingSubmitCommand) {
        Participation participation = ParticipationFindHelper.findParticipationById(participationId, participationRepository);
        participation.completeGradingOfSubmit(completeGradingSubmitCommand.getSubmitId()
                , completeGradingSubmitCommand.getStatus()
                , completeGradingSubmitCommand.getChallengeScore());
    }
}
