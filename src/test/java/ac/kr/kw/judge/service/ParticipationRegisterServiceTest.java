package ac.kr.kw.judge.service;

import ac.kr.kw.judge.challenge.domain.*;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import ac.kr.kw.judge.challenge.service.ParticipationRegisterService;
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
    @DisplayName("대회에 참여 취소")
    void 대회참여_취소_성공() {
        Challenge challenge = new Challenge(name, authors, questions, challengeDateTime);
        when(challengeRepository.findById(challengeId)).thenReturn(Optional.of(challenge));

        final Long userId = 1L;
        final String name = "tourist";
        challenge.participateInChallenge(userId, name);

        participationRegisterService.cancelParticipation(challengeId, userId);
        assertEquals(0, challenge.getParticipations().stream()
                .filter(participation -> participation.getUserId().equals(userId))
                .collect(Collectors.toList()).size(), "참여취소시 participation삭제");
    }
}
