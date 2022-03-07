package ac.kr.kw.judge.challenge.adapter.out.event;

import ac.kr.kw.judge.challenge.domain.event.Submitted;
import ac.kr.kw.judge.challenge.service.port.out.SubmitEventSender;
import ac.kr.kw.judge.commons.exception.SubmitEventPublishException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubmitEventSenderImpl implements SubmitEventSender {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void publish(Submitted submitted) {
        try {
            kafkaTemplate.send("submit", objectMapper.writeValueAsString(submitted));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new SubmitEventPublishException(e.getMessage());
        }
    }
}
