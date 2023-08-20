package com.example.apitest.service;

import com.example.apitest.model.request.LogoutApiRequest;
import com.example.apitest.model.response.LogoutApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class LogoutApiService {

    private final ApiService<LogoutApiResponse> apiService;

    public LogoutApiResponse callPostExternalServer() {
        LogoutApiRequest logoutApiRequest = LogoutApiRequest.builder()
                .tenantId("1")
                .apiKey("apikey")
                .token("Token")
                .build();

        HttpHeaders headers = new HttpHeaders();
        LogoutApiResponse response = apiService
                .post("https://d7999725-76e2-4c80-89a9-d561f0b0c1fa.mock.pstmn.io/auth/logout", headers, logoutApiRequest, LogoutApiResponse.class)
                .getBody();
        System.out.println(">>>> response = " + response);
        return response;
    }
}
