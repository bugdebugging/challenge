package ac.kr.kw.judge.challenge.repository;

import ac.kr.kw.judge.challenge.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
