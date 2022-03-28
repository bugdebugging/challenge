package ac.kr.kw.judge.challenge.service.port.in;

public interface ParticipationRegisterService {
    void participateInChallenge(Long challengeId, String username);

    void cancelParticipation(Long challengeId, String username);
}
