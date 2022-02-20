package ac.kr.kw.judge.service;

import ac.kr.kw.judge.challenge.domain.Author;
import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.domain.ChallengeDateTime;
import ac.kr.kw.judge.challenge.domain.Question;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import ac.kr.kw.judge.challenge.service.port.in.ChallengeRegisterService;
import ac.kr.kw.judge.challenge.service.command.ChallengeRegisterCommand;
import ac.kr.kw.judge.challenge.service.command.QuestionRegisterCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
public class ChallengeRegisterServiceTest {
    @Autowired
    ChallengeRegisterService challengeRegisterService;

    @MockBean
    ChallengeRepository challengeRepository;

    @Test
    @DisplayName("올바른 매개변수로 대회 등록.")
    void 대회_등록_성공() {
        final String name = "2022 new year contest";
        final List<QuestionRegisterCommand> questionRegisterCommands =
                List.of(new QuestionRegisterCommand(1L, "열혈강호1"),
                        new QuestionRegisterCommand(2L, "열혈강호2"));

        final List<Author> authors = List.of(Author.of(1L, "tourist", 3809));
        final ChallengeDateTime challengeDateTime = ChallengeDateTime.of(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(5));

        final List<Question> questions = questionRegisterCommands.stream().map(questionRegisterCommand -> Question.of(questionRegisterCommand.getProblemId(),
                questionRegisterCommand.getTitle()))
                .collect(Collectors.toList());

        Challenge challenge = new Challenge(name, authors, questions, challengeDateTime);
        when(challengeRepository.save(any(Challenge.class))).thenReturn(challenge);

        ChallengeRegisterCommand challengeRegisterCommand
                = new ChallengeRegisterCommand(authors, questionRegisterCommands, name, challengeDateTime);

        challengeRegisterService.registerChallenge(challengeRegisterCommand);
        verify(challengeRepository, atLeastOnce()).save(any(Challenge.class));
    }

    @Test
    @DisplayName("잘못된 대회 시간으로 생성 실패")
    void 잘못된_대회_시간으로_생성_실패() {
        final String name = "2022 new year contest";
        final List<QuestionRegisterCommand> questionRegisterCommands =
                List.of(new QuestionRegisterCommand(1L, "열혈강호1"),
                        new QuestionRegisterCommand(2L, "열혈강호2"));

        final List<Author> authors = List.of(Author.of(1L, "tourist", 3809));
        final ChallengeDateTime startTimeIsPastThanNow = ChallengeDateTime.of(LocalDateTime.now().minusHours(3),
                LocalDateTime.now().plusHours(3));

        final List<Question> questions = questionRegisterCommands.stream().map(questionRegisterCommand -> Question.of(questionRegisterCommand.getProblemId(),
                questionRegisterCommand.getTitle()))
                .collect(Collectors.toList());

        Challenge challenge = new Challenge(name, authors, questions, startTimeIsPastThanNow);
        when(challengeRepository.save(any(Challenge.class))).thenReturn(challenge);

        assertThrows(IllegalArgumentException.class, () -> {
            ChallengeRegisterCommand challengeRegisterCommand
                    = new ChallengeRegisterCommand(authors, questionRegisterCommands, name, startTimeIsPastThanNow);
            challengeRegisterService.registerChallenge(challengeRegisterCommand);
        }, "대회 시작 시간은 현재 시각보다 미래여야한다.");
    }
}
