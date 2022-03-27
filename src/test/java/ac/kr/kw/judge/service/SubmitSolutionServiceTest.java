package ac.kr.kw.judge.service;

import ac.kr.kw.judge.challenge.domain.*;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import ac.kr.kw.judge.challenge.repository.ParticipationRepository;
import ac.kr.kw.judge.challenge.service.port.in.SubmitSolutionService;
import ac.kr.kw.judge.challenge.service.command.CompleteGradingSubmitCommand;
import ac.kr.kw.judge.challenge.service.command.QuestionRegisterCommand;
import ac.kr.kw.judge.challenge.service.command.SolutionSubmitCommand;
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
public class SubmitSolutionServiceTest {
    @Autowired
    SubmitSolutionService submitSolutionService;

    @MockBean
    ChallengeRepository challengeRepository;

    @MockBean
    ParticipationRepository participationRepository;

    final Long challengeId = 1L;
    final String name = "new year 2022 contest.";
    final List<Author> authors = List.of(Author.of(1L, "tourist", 2800),
            Author.of(2L, "koosaga", 2700));
    final List<QuestionRegisterCommand> questionRegisterCommands = List.of(new QuestionRegisterCommand(1L, "dp1"),
            new QuestionRegisterCommand(2L, "dp2"));
    final List<Question> questions = questionRegisterCommands.stream().map(questionRegisterCommand -> Question.of(questionRegisterCommand.getProblemId(),
            questionRegisterCommand.getTitle()))
            .collect(Collectors.toList());
    final ChallengeDateTime challengeDateTime = ChallengeDateTime.of(LocalDateTime.now().minusHours(2), LocalDateTime.now().plusHours(2));

    @Test
    @DisplayName("참여자 솔루션 제출 성공")
    void 참여자_솔루션_제출() {
        final Long problemId = 2L;
        final ProgrammingLanguage language = ProgrammingLanguage.CPP;
        final String code = "source code~";

        Participation participation = Participation.of(4L, "ReReRERE", null);
        participation.submitSolutionOfQuestion(problemId, language, code);

        Submit resultSubmit = participation.getSubmits().stream()
                .filter(submit -> submit.getProblemId().equals(problemId))
                .findFirst().get();

        assertEquals(SubmitStatus.PENDING, resultSubmit.getSubmitStatus(), "초기 제출 시 상태는 PENDING");
        assertEquals(ChallengeScore.ZERO, resultSubmit.getScore(), "채점완료 전에는 0점.");
        assertEquals(language, resultSubmit.getProgrammingLanguage());
        assertEquals(code, resultSubmit.getSourceCode());
    }

    @Test
    @DisplayName("대회 종료 후 솔루션 제출")
    void 대회_종료후_제출_실패() {
        final Long participationId = 4L;
        ChallengeDateTime alreadyFinishedChallengeDateTime = ChallengeDateTime.of(LocalDateTime.now().minusHours(2),
                LocalDateTime.now().minusHours(1));
        Challenge challenge = new Challenge(name, authors, questions, alreadyFinishedChallengeDateTime);
        challenge.participateInChallenge(participationId, "ReReRERE", LocalDateTime.now().minusHours(7));
        challenge.participateInChallenge(5L, "Alpha", LocalDateTime.now().minusHours(6));
        when(challengeRepository.findChallengeWithParticipation(1L)).thenReturn(Optional.of(challenge));

        final Long problemId = 2L;
        final ProgrammingLanguage language = ProgrammingLanguage.CPP;
        final String code = "source code~";
        SolutionSubmitCommand command = new SolutionSubmitCommand(problemId, language, code, "ReReRERE");

        assertThrows(IllegalStateException.class, () -> {
            submitSolutionService.submitSolution(challengeId, command);
        }, "대회 도중에만 제출가능합니다.");
    }

    @Test
    @DisplayName("대회문제 아닌 문제 제출")
    void 대회문제_아닌_문제제출_실패() {
        final Long participationId = 4L;
        Challenge challenge = new Challenge(name, authors, questions, challengeDateTime);
        challenge.participateInChallenge(participationId, "ReReRERE", LocalDateTime.now().minusHours(7));
        challenge.participateInChallenge(5L, "Alpha", LocalDateTime.now().minusHours(6));
        when(challengeRepository.findChallengeWithParticipation(1L)).thenReturn(Optional.of(challenge));

        final Long problemIdNotExistInChallenge = 888L;
        final ProgrammingLanguage language = ProgrammingLanguage.CPP;
        final String code = "source code!!";
        SolutionSubmitCommand command = new SolutionSubmitCommand(problemIdNotExistInChallenge, language, code, "ReReRERE");

        assertThrows(IllegalArgumentException.class, () -> {
            submitSolutionService.submitSolution(challengeId, command);
        }, "대회 도중에만 제출가능합니다.");
    }

    @Test
    @DisplayName("참여자 점수계산")
    void 참여자_대회점수계산() {
        List<Submit> submits = List.of(Submit.withId(1L, 1L, null, ProgrammingLanguage.CPP, "failedSubmits1")
                , Submit.withId(2L, 1L, null, ProgrammingLanguage.JAVA, "successSubmits1")
                , Submit.withId(3L, 2L, null, ProgrammingLanguage.C, "successSubmits2")
                , Submit.withId(4L, 2L, null, ProgrammingLanguage.C, "failedSubmits2")
                , Submit.withId(5L, 3L, null, ProgrammingLanguage.C, "successSubmits3")
                , Submit.withId(6L, 3L, null, ProgrammingLanguage.C, "PENDING Submit"));

        Challenge challenge = new Challenge(name, authors, questions, challengeDateTime);

        Participation participation = Participation.withSubmits(1L, "tourist", null, submits);

        when(challengeRepository.findById(1L)).thenReturn(Optional.of(challenge));
        when(participationRepository.findParticipationByChallengeAndName(challenge, "tourist"))
                .thenReturn(Optional.of(participation));

        List<CompleteGradingSubmitCommand> successSubmits = List.of(
                new CompleteGradingSubmitCommand(2L, 1L, "tourist", SubmitStatus.SUCCESS, ChallengeScore.of(700))
                , new CompleteGradingSubmitCommand(3L, 1L, "tourist", SubmitStatus.SUCCESS, ChallengeScore.of(1000))
                , new CompleteGradingSubmitCommand(5L, 1L, "tourist", SubmitStatus.SUCCESS, ChallengeScore.of(900)));

        List<CompleteGradingSubmitCommand> failedSubmits = List.of(
                new CompleteGradingSubmitCommand(1L, 1L, "tourist", SubmitStatus.FAILED, ChallengeScore.of(0))
                , new CompleteGradingSubmitCommand(4L, 1L, "tourist", SubmitStatus.FAILED, ChallengeScore.of(0)));

        successSubmits.stream()
                .forEach(successSubmit -> submitSolutionService.completeGradingOfSubmit("tourist", successSubmit));
        failedSubmits.stream()
                .forEach(failedSubmit -> submitSolutionService.completeGradingOfSubmit("tourist", failedSubmit));

        ChallengeScore expectScore = ChallengeScore.of(2600);
        assertEquals(expectScore, participation.getChallengeScore(), "참여자의 점수는 각 문제별 최대 득점의 합.");
    }
}
