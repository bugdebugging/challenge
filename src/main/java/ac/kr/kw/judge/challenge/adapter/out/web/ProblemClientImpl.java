package ac.kr.kw.judge.challenge.adapter.out.web;

import ac.kr.kw.judge.challenge.dto.in.ProblemDto;
import ac.kr.kw.judge.commons.api.ApiResult;
import ac.kr.kw.judge.commons.exception.ProblemClientException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProblemClientImpl implements ProblemClient {
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private String url;

    @Autowired
    public ProblemClientImpl(RestTemplateBuilder builder, ObjectMapper objectMapper, @Value("${rest-client.problem-catalog}") String url) {
        this.restTemplate = builder.build();
        this.objectMapper = objectMapper;
        this.url = url;
    }

    @Override
    public List<ProblemDto> findProblemsContainingIds(List<Long> problemIds) {
        String urlWithParams = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("problemIds", StringUtils.join(problemIds, ','))
                .toUriString();

        ResponseEntity<String> response = restTemplate.getForEntity(urlWithParams, String.class);
        if (response.getStatusCode() == HttpStatus.ACCEPTED) {
            throw new IllegalArgumentException("해당 id의 문제들을 찾을 수 없습니다.");
        }
        return convertToProblemDto(response);
    }

    private List<ProblemDto> convertToProblemDto(ResponseEntity<String> response) {
        try {
            ApiResult<List<LinkedHashMap>> result = objectMapper.readValue(response.getBody(), ApiResult.class);
            return result.getData().stream()
                    .map(linkedHashMap -> new ProblemDto(Long.valueOf((Integer) linkedHashMap.get("id")), (String) linkedHashMap.get("name")))
                    .collect(Collectors.toList());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ProblemClientException(e.getMessage());
        }
    }
}
