package ac.kr.kw.judge.challenge.service.port.in;

import ac.kr.kw.judge.challenge.dto.out.SubmitItemDto;

import java.util.List;

public interface ParticipationSearchService {
    List<SubmitItemDto> findSubmitListOfParticipation(Long participationId);

    SubmitItemDto findSubmitDetail(Long submitId);
}
