package ac.kr.kw.judge.service;

import ac.kr.kw.judge.challenge.domain.*;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import ac.kr.kw.judge.challenge.service.command.QuestionRegisterCommand;
import ac.kr.kw.judge.challenge.service.port.in.ParticipationRegisterService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ParticipationRegisterServiceTest {
    @Autowired
    ParticipationRegisterService participationRegisterService;

    @MockBean
    ChallengeRepository challengeRepository;

    final Long challengeId = 1L;
    final String name = "new year 2022 contest.";
    final Author author = Author.of("tourist");
    final List<QuestionRegisterCommand> questionRegisterCommands = List.of(new QuestionRegisterCommand(1L, "dp1"),
            new QuestionRegisterCommand(2L, "dp2"));
    final ChallengeDateTime challengeDateTime = ChallengeDateTime.of(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(5));
    final List<Question> questions = questionRegisterCommands.stream()
            .map(questionRegisterCommand -> Question.of(questionRegisterCommand.getProblemId(), questionRegisterCommand.getTitle()))
            .collect(Collectors.toList());

    @Test
    @DisplayName("대회에 참여")
    void 대회참여_성공() {
        Challenge challenge = new Challenge(name, questions, challengeDateTime, author);
        when(challengeRepository.findChallengeWithParticipation(challengeId)).thenReturn(Optional.of(challenge));

        final String name = "tourist";
        participationRegisterService.participateInChallenge(challengeId, name);

        Participation expect = challenge.getParticipations().stream()
                .filter(participation -> participation.getName().equals(name))
                .findFirst().get();

        assertEquals(name, expect.getName());
    }

    @Test
    @DisplayName("대회에 중복 참여")
    void 대회_중복참여_실패() {
        Challenge challenge = new Challenge(name, questions, challengeDateTime, author);
        when(challengeRepository.findChallengeWithParticipation(challengeId)).thenReturn(Optional.of(challenge));

        final String name = "tourist";
        participationRegisterService.participateInChallenge(challengeId, name);

        assertThrows(IllegalStateException.class, () -> {
            participationRegisterService.participateInChallenge(challengeId, name);
        }, "대회에 중복참여할 수는 없다.");
    }

    @Test
    @DisplayName("대회 시작후 참여 실패")
    void 대회시작후_참여_실패() {
        ChallengeDateTime onProgressDateTime = ChallengeDateTime.of(LocalDateTime.now().minusHours(1)
                , LocalDateTime.now().plusHours(1));
        Challenge challenge = new Challenge(name, questions, onProgressDateTime, author);
        when(challengeRepository.findChallengeWithParticipation(challengeId)).thenReturn(Optional.of(challenge));

        assertThrows(IllegalStateException.class, () -> {
            participationRegisterService.participateInChallenge(1L, name);
        }, "대회 시작 전에만 참여가 가능하다.");
    }
}
