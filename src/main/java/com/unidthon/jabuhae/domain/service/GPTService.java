package com.unidthon.jabuhae.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.unidthon.jabuhae.domain.dto.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class GPTService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public GPTService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private List<Message> createRecommendMessages(List<String> gradients, List<String> tools) {
        List<Message> messages = new ArrayList<>();

        messages.add(new Message("system",
                "유저의 입력값: 재료 - " + gradients.toString() + ", 도구 - " + tools.toString() +
                        ". 이 입력값을 바탕으로 4개의 요리를 추천해 주세요.\n" +
                        "2개는 입력된 재료를 우선으로 해서 만들 수 있는 요리이며 나머지 2개는 입력된 조리 도구를 우선으로 해서 만들 수 있는 요리입니다."+
                        "JSON 형식으로 응답하고, 각 항목은 요리명, 메인 재료, 서브 재료, 완성도를 포함해야 합니다.\n\n" +
                        "1. **요리명: <요리 이름>**\n" +
                        "   - 메인 재료: <해당 요리에 사용되는 메인 재료 1개>\n" +
                        "   - 서브 재료: <해당 요리에 사용되는 서브 재료 1개 (굳이 사용자가 입력한 재료가 아니어도 됨)>\n" +
                        "   - 완성도: <해당 요리의 난이도를 1 ~ 100으로 표시>\n\n" +
                        "해당 형식 외의 부가적인 말은 제공하지 마세요, 재료,도구,레시피에 관해서는 자세하게 제공해주세요."
        ));

        return messages;
    }

    private List<Message> createRecipeMessages(List<String> gradients, List<String> tools, String name) {
        List<Message> messages = new ArrayList<>();

        messages.add(new Message("system","당신은 요리사입니다. 다음 재료와 조리 도구를 활용해 다음 음식의 레시피를 제공해 주세요.\n\n"
                + "요리 이름: " + name.toString() + "\n"
                + "재료: " + String.join(", ", gradients) + "\n"
                + "조리 도구: " + String.join(", ", tools) + "\n"
                + "JSON 형식으로 응답하고, 각 항목은 요리명, 조리 시간, 인분, 재료 목록, 필요 도구, 조리 과정, 팁을 포함해야 합니다.\n\n"
                + "- **조리 시간:**\n"
                + "- **인분:**\n"
                + "- **재료 목록:** 사용자가 제공한 재료를 포함한 전체 재료 목록을 작성하고, 필요한 추가 재료를 추천해 주세요.\n"
                + "- **필요 도구:** 요리에 필요한 도구 목록을 작성하고, 필요한 추가 도구가 있다면 추가 도구를 추천해주세요.\n"
                + "- **조리 과정:** 간단하고 따라하기 쉬운 단계별 설명을 제공해 주세요.\n"
                + "- **팁:** 요리를 더 맛있게 할 수 있는 추가 정보를 제공해 주세요."
                + "해당 형식 외의 부가적인 말은 제공하지 마세요."));
        return messages;
    }

    private List<Message> createChangeMessages(List<String> gradients) {
        List<Message> messages = new ArrayList<>();

        messages.add(new Message("system","당신은 요리사입니다. 다음 재료를 대체할 수 있는 재료를 추천해주세요.\n\n"
                + "재료: " + String.join(", ", gradients) + "\n"
                + "JSON 형식으로 응답하고, 기존 재료와 대체 재료를 담습니다.\n\n"
                + "기존재료 : 대체재료 형식의 json으로 제공해주세요."
                + "대체 재료가 마땅히 없다면 제외하는 걸로 제공하세요."
                + "해당 형식 외의 부가적인 말은 제공하지 마세요."));
        return messages;
    }


    public String getRecommendResponse(List<String> gradients, List<String> tools) {

        List<Message> messages = createRecommendMessages(gradients, tools);
        // Headers 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        // 요청 데이터 생성
        GPTRequest request = new GPTRequest("gpt-3.5-turbo", messages);

        // API 요청
        HttpEntity<GPTRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<GPTResponse> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                entity,
                GPTResponse.class
        );

        // 응답 처리
        return response.getBody()
                .getChoices()
                .get(0)
                .getMessage()
                .getContent();
    }

    public String getRecipeResponse(List<String> gradients, List<String> tools, String name) {

        List<Message> messages = createRecipeMessages(gradients, tools, name);
        // Headers 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        // 요청 데이터 생성
        GPTRequest request = new GPTRequest("gpt-3.5-turbo", messages);

        // API 요청
        HttpEntity<GPTRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<GPTResponse> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                entity,
                GPTResponse.class
        );

        // 응답 처리
        return response.getBody()
                .getChoices()
                .get(0)
                .getMessage()
                .getContent();
    }

    public String getChangeResponse(List<String> gradients) {

        List<Message> messages = createChangeMessages(gradients);
        // Headers 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        // 요청 데이터 생성
        GPTRequest request = new GPTRequest("gpt-3.5-turbo", messages);

        // API 요청
        HttpEntity<GPTRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<GPTResponse> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                entity,
                GPTResponse.class
        );

        // 응답 처리
        return response.getBody()
                .getChoices()
                .get(0)
                .getMessage()
                .getContent();
    }

}
