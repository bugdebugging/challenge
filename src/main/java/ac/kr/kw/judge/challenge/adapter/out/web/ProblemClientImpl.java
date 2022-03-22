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

import static com.google.common.base.Preconditions.checkArgument;

@Component
public class ProblemClientImpl implements ProblemClient {
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private String host;
    private static final String GET_PROBLEM_DTO_CONTAINING_IDS = "/api/problem_catalogs/containing";

    @Autowired
    public ProblemClientImpl(RestTemplateBuilder builder
            , ObjectMapper objectMapper
            , @Value("${rest-client.problem-catalog.host}") String host) {
        this.restTemplate = builder.build();
        this.objectMapper = objectMapper;
        this.host = host;
    }

    @Override
    public List<ProblemDto> findProblemsContainingIds(List<Long> problemIds) {
        String urlWithParams = UriComponentsBuilder.fromHttpUrl(host + GET_PROBLEM_DTO_CONTAINING_IDS)
                .queryParam("problemIds", StringUtils.join(problemIds, ','))
                .toUriString();

        ResponseEntity<String> response = restTemplate.getForEntity(urlWithParams, String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new IllegalArgumentException("문제를 탐색할 수 없습니다.");
        }
        List<ProblemDto> result = convertToProblemDto(response);
        checkArgument(result.size() == problemIds.size(), "해당 id의 문제들을 찾을 수 없습니다.");
        return result;
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
