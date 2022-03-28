package ac.kr.kw.judge.challenge.service.port.out;

import ac.kr.kw.judge.challenge.dto.in.ProblemDto;

import java.util.List;

public interface ProblemClient {
    List<ProblemDto> findProblemsContainingIds(List<Long> problemIds, String username);
}
