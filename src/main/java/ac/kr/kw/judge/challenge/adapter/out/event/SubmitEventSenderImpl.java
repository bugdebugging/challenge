package ac.kr.kw.judge.challenge.adapter.out.event;

import ac.kr.kw.judge.challenge.service.port.out.EventSender;
import ac.kr.kw.judge.commons.exception.EventPublishFailedException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubmitEventSenderImpl implements EventSender {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void publish(String topic, Object data) {
        try {
            kafkaTemplate.send(topic, objectMapper.writeValueAsString(data));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new EventPublishFailedException(e.getMessage());
        }
    }

    @Override
    public void publishWithKey(String topic, String key, Object data) {
        try {
            kafkaTemplate.send(topic, key, objectMapper.writeValueAsString(data));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new EventPublishFailedException(e.getMessage());
        }
    }
}
