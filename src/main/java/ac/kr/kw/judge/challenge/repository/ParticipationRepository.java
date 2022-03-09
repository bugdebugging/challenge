package ac.kr.kw.judge.challenge.repository;

import ac.kr.kw.judge.challenge.domain.Challenge;
import ac.kr.kw.judge.challenge.domain.Participation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    List<Participation> findParticipationByChallenge(Challenge challenge, Pageable pageable);

    Optional<Participation> findParticipationByChallengeAndUserId(Challenge challenge, Long userId);
}
