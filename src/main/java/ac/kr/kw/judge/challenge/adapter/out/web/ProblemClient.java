package ac.kr.kw.judge.challenge.adapter.out.web;

import ac.kr.kw.judge.challenge.dto.in.ProblemDto;

import java.util.List;

public interface ProblemClient {
    List<ProblemDto> findProblemsContainingIds(List<Long> problemIds);
}
