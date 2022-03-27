package ac.kr.kw.judge.challenge.service.port.in;

import ac.kr.kw.judge.challenge.dto.out.SubmitDetailDto;
import ac.kr.kw.judge.challenge.dto.out.SubmitItemDto;

import java.util.List;

public interface ParticipationSearchService {
    List<SubmitItemDto> findSubmitListOfParticipation(Long challengeId, String username);

    SubmitDetailDto findSubmitDetail(Long challengeId, String username, Long submitId);

}
