package ac.kr.kw.judge.challenge.repository;

import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.dto.ChallengeListItemDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    @Query("SELECT new ac.kr.kw.judge.challenge.dto.ChallengeListItemDto(C.id,C.name,C.challengeDateTime " +
            ",C.participations.size, C.challengeQuestions.size) " +
            "FROM Challenge C")
    List<ChallengeListItemDto> findChallengeWithCountOfQuestionAndParticipation(Pageable page);
}
