package ac.kr.kw.judge.challenge.adapter.in.web;

import ac.kr.kw.judge.challenge.service.port.in.ChallengeGradeService;
import ac.kr.kw.judge.commons.api.ApiResult;
import ac.kr.kw.judge.commons.api.ApiUtils;
import ac.kr.kw.judge.commons.auth.AuthorizedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChallengeGradeController {
    private final ChallengeGradeService challengeGradeService;

    @PostMapping("/api/challenges/{challengeId}/grading")
    public ApiResult gradeChallenge(@PathVariable("challengeId") Long challengeId,
                                    @AuthorizedUser String username) {
        challengeGradeService.gradeChallenge(challengeId, username);
        return ApiUtils.simpleMessage("challenge grading is successfully requested.");
    }
}
