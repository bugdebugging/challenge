package ac.kr.kw.judge.challenge.repository;

import ac.kr.kw.judge.challenge.domain.Participation;
import ac.kr.kw.judge.challenge.domain.Submit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmitRepository extends JpaRepository<Submit, Long> {
    List<Submit> findSubmitByParticipation(Participation participation);
}
