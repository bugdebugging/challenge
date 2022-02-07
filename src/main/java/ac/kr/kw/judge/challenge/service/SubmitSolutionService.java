package ac.kr.kw.judge.challenge.service;

import ac.kr.kw.judge.challenge.service.command.SolutionSubmitCommand;

public interface SubmitSolutionService {
    Long submitSolution(Long challengeId, SolutionSubmitCommand solutionSubmitCommand);
}
