package ac.kr.kw.judge.repository;

import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.domain.Question;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import ac.kr.kw.judge.challenge.repository.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QuestionRepositoryTest {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ChallengeRepository challengeRepository;

    @Test
    @DisplayName("대회에 출제되는 문제 조회")
    void 대회에_출제되는문제_조회() {
        final Long challengeId = 1L;
        Challenge challenge = challengeRepository.findById(challengeId).get();
        List<Question> questions = questionRepository.findQuestionByChallenge(challenge);

        assertEquals(4, questions.size());

        assertEquals(1L, questions.get(0).getId());
        assertEquals(2L, questions.get(1).getId());
        assertEquals(3L, questions.get(2).getId());
        assertEquals(4L, questions.get(3).getId());
    }
}
