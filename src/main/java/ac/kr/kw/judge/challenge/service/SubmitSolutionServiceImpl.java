package ac.kr.kw.judge.challenge.service;

import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.domain.Participation;
import ac.kr.kw.judge.challenge.domain.Submit;
import ac.kr.kw.judge.challenge.domain.event.Submitted;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import ac.kr.kw.judge.challenge.repository.ParticipationRepository;
import ac.kr.kw.judge.challenge.repository.SubmitRepository;
import ac.kr.kw.judge.challenge.service.port.in.SubmitSolutionService;
import ac.kr.kw.judge.challenge.service.command.CompleteGradingSubmitCommand;
import ac.kr.kw.judge.challenge.service.command.SolutionSubmitCommand;
import ac.kr.kw.judge.challenge.service.port.out.EventSender;
import ac.kr.kw.judge.commons.exception.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubmitSolutionServiceImpl implements SubmitSolutionService {
    private final ChallengeRepository challengeRepository;
    private final ParticipationRepository participationRepository;
    private final SubmitRepository submitRepository;
    private final EventSender eventSender;

    @Override
    public void submitSolution(Long challengeId, SolutionSubmitCommand solutionSubmitCommand) {
        Challenge challenge = ChallengeFindHelper.findChallengeById(challengeId, challengeRepository);
        Participation participation = participationRepository.findParticipationByChallengeAndUserId(challenge, solutionSubmitCommand.getParticipationId())
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("해당 사용자가 없습니다.");
                });
        if (!participation.getName().equals(solutionSubmitCommand.getUsername())) {
            throw new UnAuthorizedException("본인의 솔루션만 제출할 수 있습니다.");
        }
        Submit submit = challenge.submitSolutionOfQuestion(solutionSubmitCommand.getParticipationId()
                , solutionSubmitCommand.getProblemId()
                , solutionSubmitCommand.getProgrammingLanguage()
                , solutionSubmitCommand.getSourceCode());

        submitRepository.save(submit);
        eventSender.publish("submit", Submitted.of(solutionSubmitCommand.getProblemId()
                , solutionSubmitCommand.getParticipationId()
                , submit.getId()
                , solutionSubmitCommand.getProgrammingLanguage().getValue()
                , solutionSubmitCommand.getSourceCode()));
    }

    @Override
    public void completeGradingOfSubmit(Long participationId, CompleteGradingSubmitCommand completeGradingSubmitCommand) {
        Participation participation = ParticipationFindHelper.findParticipationById(participationId, participationRepository);
        participation.completeGradingOfSubmit(completeGradingSubmitCommand.getSubmitId()
                , completeGradingSubmitCommand.getStatus()
                , completeGradingSubmitCommand.getChallengeScore());
    }
}
