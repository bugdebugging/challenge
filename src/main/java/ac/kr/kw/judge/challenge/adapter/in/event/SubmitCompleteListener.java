package ac.kr.kw.judge.challenge.adapter.in.event;

import ac.kr.kw.judge.challenge.domain.ChallengeScore;
import ac.kr.kw.judge.challenge.domain.SubmitStatus;
import ac.kr.kw.judge.challenge.domain.event.CompletedSubmit;
import ac.kr.kw.judge.challenge.domain.event.GradingStatus;
import ac.kr.kw.judge.challenge.service.command.CompleteGradingSubmitCommand;
import ac.kr.kw.judge.challenge.service.port.in.SubmitSolutionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubmitCompleteListener {
    private final ObjectMapper objectMapper;
    private final SubmitSolutionService submitSolutionService;

    @KafkaListener(topics = "grade", groupId = "grade-consumer-group")
    public void consumeCompleteGradingEvent(String message) {
        try {
            CompletedSubmit completedSubmit = objectMapper.readValue(message, CompletedSubmit.class);
            CompleteGradingSubmitCommand completeGradingSubmitCommand = new CompleteGradingSubmitCommand(completedSubmit.getSubmitId()
                    , completedSubmit.getChallengeId()
                    , completedSubmit.getUsername()
                    , completedSubmit.getResult().getStatus() == GradingStatus.SUCCESS ? SubmitStatus.SUCCESS : SubmitStatus.FAILED
                    , ChallengeScore.of(completedSubmit.getResult().getScore()));
            submitSolutionService.completeGradingOfSubmit(completedSubmit.getUsername(), completeGradingSubmitCommand);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
