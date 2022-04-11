package ac.kr.kw.judge.challenge.service.port;

import ac.kr.kw.judge.challenge.domain.Author;
import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.domain.event.ParticipantResult;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import ac.kr.kw.judge.challenge.service.helper.ChallengeFindHelper;
import ac.kr.kw.judge.challenge.service.port.in.ChallengeGradeService;
import ac.kr.kw.judge.challenge.service.port.out.EventSender;
import ac.kr.kw.judge.commons.exception.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ChallengeGradeServiceImpl implements ChallengeGradeService {
    private final ChallengeRepository challengeRepository;
    private final EventSender eventSender;
    private static final String CHALLENGE_FINISH_TOPIC_NAME = "challengeFinish";

    @Override
    public void gradeChallenge(Long challengeId, String username) {
        Challenge challenge = ChallengeFindHelper.findByIdWithParticipation(challengeId, challengeRepository);
        if (challenge.getAuthor().equals(Author.of(username))) {
            throw new UnAuthorizedException("대회 채점 요청은 대회 개최자만이 수행할 수 있습니다.");
        }
        eventSender.publish(CHALLENGE_FINISH_TOPIC_NAME, challenge.getParticipations().stream()
                .map(participation -> ParticipantResult.of(participation.getName(), participation.getChallengeScore().getScore()))
                .collect(Collectors.toList()));
    }
}
