package ac.kr.kw.judge.challenge.service;

import ac.kr.kw.judge.challenge.service.command.ChallengeInfoModifyCommand;

public interface ChallengeModifyService {
    Long addQuestion(Long challengeId, Long problemId, String title);

    void deleteQuestion(Long challengeId, Long questionId);

    void changeChallengeInfo(Long challengeId, ChallengeInfoModifyCommand challengeInfoModifyCommand);
}
