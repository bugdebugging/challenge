package ac.kr.kw.judge.repository;

import ac.kr.kw.judge.challenge.domain.Author;
import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.dto.out.ChallengeListItemDto;
import ac.kr.kw.judge.challenge.repository.ChallengeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ChallengeRepositoryTest {
    @Autowired
    ChallengeRepository challengeRepository;

    @Test
    @DisplayName("대회_id_정렬조회")
    void 대회_id_정렬조회() {
        List<ChallengeListItemDto> result = challengeRepository
                .findChallengeWithCountOfQuestionAndParticipation(PageRequest.of(0, 2, Sort.by("id").descending()))
                .stream().map(objects -> new ChallengeListItemDto((Challenge) objects[0], (int) objects[1], (int) objects[2]))
                .collect(Collectors.toList());

        assertEquals(2, result.size());

        assertEquals("Global Round", result.get(0).getName());
        assertEquals("Educational Round", result.get(1).getName());

        assertEquals(5L, result.get(0).getId());
        assertEquals(4L, result.get(1).getId());

        assertEquals(1, result.get(0).getNumOfQuestion());
        assertEquals(1, result.get(1).getNumOfQuestion());

        assertEquals(2, result.get(0).getNumOfParticipation());
        assertEquals(1, result.get(1).getNumOfParticipation());

        assertEquals(1, result.get(0).getAuthors().size());
        assertTrue(result.get(0).getAuthors().contains(Author.of(402L, "koru", 2200)));

        assertEquals(3, result.get(1).getAuthors().size());
        assertTrue(result.get(1).getAuthors().contains(Author.of(400L, "vovuh", 2150)));
        assertTrue(result.get(1).getAuthors().contains(Author.of(401L, "kisa", 1846)));
        assertTrue(result.get(1).getAuthors().contains(Author.of(402L, "koru", 2200)));
    }

    @Test
    @DisplayName("대회_시작시간_정렬조회")
    void 대회_시작시간_정렬조회() {
        List<ChallengeListItemDto> result = challengeRepository
                .findChallengeWithCountOfQuestionAndParticipation(PageRequest.of(0, 3, Sort.by("challengeDateTime.startTime").ascending()))
                .stream().map(objects -> new ChallengeListItemDto((Challenge) objects[0], (int) objects[1], (int) objects[2]))
                .collect(Collectors.toList());

        assertEquals(3, result.size());

        assertEquals("round513 Div1", result.get(0).getName());
        assertEquals("round512 Div3", result.get(1).getName());
        assertEquals("round511 Div2", result.get(2).getName());

        assertEquals(3L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
        assertEquals(1L, result.get(2).getId());

        assertEquals(0, result.get(0).getNumOfQuestion());
        assertEquals(2, result.get(1).getNumOfQuestion());
        assertEquals(4, result.get(2).getNumOfQuestion());

        assertEquals(3, result.get(0).getNumOfParticipation());
        assertEquals(0, result.get(1).getNumOfParticipation());
        assertEquals(3, result.get(2).getNumOfParticipation());

        assertEquals(0, result.get(0).getAuthors().size());

        assertEquals(1, result.get(1).getAuthors().size());
        assertTrue(result.get(1).getAuthors().contains(Author.of(101L, "tester2", 2500)));

        assertEquals(2, result.get(2).getAuthors().size());
        assertTrue(result.get(2).getAuthors().contains(Author.of(101L, "tester2", 2500)));
        assertTrue(result.get(2).getAuthors().contains(Author.of(100L, "tester1", 2000)));
    }
}
