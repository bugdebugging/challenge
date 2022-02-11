package ac.kr.kw.judge.challenge.repository;

import ac.kr.kw.judge.challenge.domain.Challenge;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    @Query("SELECT distinct C, C.participations.size, C.challengeQuestions.size " +
            "FROM Challenge C LEFT JOIN FETCH C.authors")
    List<Object[]> findChallengeWithCountOfQuestionAndParticipation(Pageable page);
}
