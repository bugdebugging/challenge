package ac.kr.kw.judge.service;

import ac.kr.kw.judge.challenge.domain.*;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import ac.kr.kw.judge.challenge.service.port.in.ParticipationRegisterService;
import ac.kr.kw.judge.challenge.service.command.ParticipationRegisterCommand;
import ac.kr.kw.judge.challenge.service.command.QuestionRegisterCommand;
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
    final List<Author> authors = List.of(Author.of(1L, "tourist", 2800),
            Author.of(2L, "koosaga", 2700));
    final List<QuestionRegisterCommand> questionRegisterCommands = List.of(new QuestionRegisterCommand(1L, "dp1"),
            new QuestionRegisterCommand(2L, "dp2"));
    final ChallengeDateTime challengeDateTime = ChallengeDateTime.of(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(5));
    final List<Question> questions = questionRegisterCommands.stream()
            .map(questionRegisterCommand -> Question.of(questionRegisterCommand.getProblemId(), questionRegisterCommand.getTitle()))
            .collect(Collectors.toList());

    @Test
    @DisplayName("대회에 참여")
    void 대회참여_성공() {
        Challenge challenge = new Challenge(name, authors, questions, challengeDateTime);
        when(challengeRepository.findById(challengeId)).thenReturn(Optional.of(challenge));

        final Long userId = 1L;
        final String name = "tourist";
        participationRegisterService.participateInChallenge(challengeId, new ParticipationRegisterCommand(userId, name));

        Participation expect = challenge.getParticipations().stream()
                .filter(participation -> participation.getUserId().equals(userId))
                .findFirst().get();

        assertEquals(userId, expect.getUserId());
        assertEquals(name, expect.getName());
    }

    @Test
    @DisplayName("대회에 중복 참여")
    void 대회_중복참여_실패() {
        Challenge challenge = new Challenge(name, authors, questions, challengeDateTime);
        when(challengeRepository.findById(challengeId)).thenReturn(Optional.of(challenge));

        final Long userId = 1L;
        final String name = "tourist";
        participationRegisterService.participateInChallenge(challengeId, new ParticipationRegisterCommand(userId, name));

        assertThrows(IllegalStateException.class, () -> {
            participationRegisterService.participateInChallenge(challengeId, new ParticipationRegisterCommand(userId, name));
        }, "대회에 중복참여할 수는 없다.");
    }

    @Test
    @DisplayName("대회에 참여 취소")
    void 대회참여_취소_성공() {
        Challenge challenge = new Challenge(name, authors, questions, challengeDateTime);
        when(challengeRepository.findById(challengeId)).thenReturn(Optional.of(challenge));

        final Long userId = 1L;
        final String name = "tourist";
        challenge.participateInChallenge(userId, name, LocalDateTime.now());

        participationRegisterService.cancelParticipation(challengeId, userId);
        assertEquals(0, challenge.getParticipations().stream()
                .filter(participation -> participation.getUserId().equals(userId))
                .collect(Collectors.toList()).size(), "참여취소시 participation삭제");
    }

    @Test
    @DisplayName("대회 시작후 참여 실패")
    void 대회시작후_참여_실패() {
        ChallengeDateTime onProgressDateTime = ChallengeDateTime.of(LocalDateTime.now().minusHours(1)
                , LocalDateTime.now().plusHours(1));
        Challenge challenge = new Challenge(name, authors, questions, onProgressDateTime);
        when(challengeRepository.findById(challengeId)).thenReturn(Optional.of(challenge));

        assertThrows(IllegalStateException.class, () -> {
            participationRegisterService.participateInChallenge(1L, new ParticipationRegisterCommand(7L, "rein"));
        }, "대회 시작 전에만 참여가 가능하다.");
    }
}
