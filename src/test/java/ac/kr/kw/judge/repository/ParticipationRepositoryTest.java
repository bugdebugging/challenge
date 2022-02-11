package ac.kr.kw.judge.repository;

import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.domain.Participation;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import ac.kr.kw.judge.challenge.repository.ParticipationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ParticipationRepositoryTest {
    @Autowired
    ParticipationRepository participationRepository;

    @Autowired
    ChallengeRepository challengeRepository;

    @Test
    @DisplayName("대회 참여자 목록 점수정렬 조회")
    void 대회_참여자목록_점수정렬_조회() {
        final Long challengeId = 1L;
        Challenge challenge = challengeRepository.findById(challengeId).get();
        List<Participation> participations = participationRepository.findParticipationByChallenge(challenge,
                PageRequest.of(0, 5, Sort.by("challengeScore.score").descending()));

        assertEquals(3, participations.size());

        assertEquals(2L, participations.get(0).getId());
        assertEquals(1L, participations.get(1).getId());
        assertEquals(3L, participations.get(2).getId());
    }
}