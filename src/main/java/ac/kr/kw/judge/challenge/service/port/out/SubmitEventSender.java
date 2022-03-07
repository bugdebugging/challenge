package ac.kr.kw.judge.challenge.service.port.out;

import ac.kr.kw.judge.challenge.domain.event.Submitted;

public interface SubmitEventSender {
    void publish(Submitted submitted);
}
