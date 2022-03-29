package ac.kr.kw.judge.service;

import ac.kr.kw.judge.challenge.domain.Author;
import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.domain.ChallengeDateTime;
import ac.kr.kw.judge.challenge.domain.Question;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import ac.kr.kw.judge.challenge.service.port.in.ChallengeModifyService;
import ac.kr.kw.judge.challenge.service.command.ChallengeInfoModifyCommand;
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
public class ChallengeModifyServiceTest {
    @Autowired
    ChallengeModifyService challengeModifyService;

    @MockBean
    ChallengeRepository challengeRepository;

    final Long challengeId = 1L;
    final String name = "new year 2022 contest.";
    final Author author = Author.of("tourist");
    final List<QuestionRegisterCommand> questionRegisterCommands = List.of(new QuestionRegisterCommand(1L, "dp1"),
            new QuestionRegisterCommand(2L, "dp2"));
    final ChallengeDateTime challengeDateTime = ChallengeDateTime.of(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(5));


    @Test
    @DisplayName("대회의 문제 수정.")
    void 대회문제_수정_성공() {
        Challenge challenge = new Challenge(name,
                questionRegisterCommands.stream().map(question -> question.toEntity()).collect(Collectors.toList()),
                challengeDateTime, author);

        when(challengeRepository.findById(challengeId)).thenReturn(Optional.of(challenge));

        final List<QuestionRegisterCommand> willBeChallengeQuestions = List.of(new QuestionRegisterCommand(3L, "greedy1"),
                new QuestionRegisterCommand(4L, "greedy2"));

        //when
        challengeModifyService.changeQuestions(challengeId, author.getName(), willBeChallengeQuestions);

        //then
        for (int i = 0; i < challenge.getChallengeQuestions().size(); i++) {
            QuestionRegisterCommand expect = willBeChallengeQuestions.get(i);
            Question real = challenge.getChallengeQuestions().get(i);

            assertEquals(expect.getProblemId(), real.getProblemId(), "새로운 문제 번호로 수정");
            assertEquals(expect.getTitle(), real.getTitle(), "새로운 문제 제목으로 수정");
        }
    }

    @DisplayName("대회 정보 수정")
    void 대회정보_수정_성공() {
        Challenge challenge = new Challenge(name,
                questionRegisterCommands.stream().map(question -> question.toEntity()).collect(Collectors.toList()),
                challengeDateTime, author);

        when(challengeRepository.findById(challengeId)).thenReturn(Optional.of(challenge));

        final String willBeChallengeName = "ICPC World Final";
        final ChallengeDateTime willBeChallengeDate = ChallengeDateTime.of(LocalDateTime.now().plusHours(5),
                LocalDateTime.now().plusHours(9));

        challengeModifyService.changeChallengeInfo(challengeId, author.getName(), new ChallengeInfoModifyCommand(willBeChallengeName, willBeChallengeDate));

        assertEquals(willBeChallengeName, challenge.getName(), "수정된 이름으로 변경");
        assertEquals(willBeChallengeDate, challenge.getChallengeDateTime(), "수정된 대회시간으로 변경");
    }
}
