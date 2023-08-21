package com.example.apitest.service;

import com.example.apitest.model.request.LogoutApiRequest;
import com.example.apitest.model.request.PushEventApiRequest;
import com.example.apitest.model.response.LogoutApiResponse;
import com.example.apitest.model.response.PushEventApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PushEventApiService {

    private final ApiService<PushEventApiResponse> apiService;

    public PushEventApiResponse callPostExternalServer() {
        PushEventApiRequest pushEventApiRequest = PushEventApiRequest.builder()
                .tenantId("1")
                .apiKey("apikey")
                .token("Token")
                .message("test message")
                .receiverId(1)
                .build();

        HttpHeaders headers = new HttpHeaders();
        PushEventApiResponse response = apiService
                .post("https://d7999725-76e2-4c80-89a9-d561f0b0c1fa.mock.pstmn.io/notification", headers, pushEventApiRequest, PushEventApiResponse.class)
                .getBody();
        System.out.println(">>>> response = " + response);
        return response;
    }
}
