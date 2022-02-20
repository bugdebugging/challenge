package ac.kr.kw.judge.challenge.service.port.in;

import ac.kr.kw.judge.challenge.service.command.CompleteGradingSubmitCommand;
import ac.kr.kw.judge.challenge.service.command.SolutionSubmitCommand;

public interface SubmitSolutionService {
    void submitSolution(Long challengeId, SolutionSubmitCommand solutionSubmitCommand);
    void completeGradingOfSubmit(Long participationId, CompleteGradingSubmitCommand completeGradingSubmitCommand);
}